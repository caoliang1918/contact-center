#!/bin/sh

APP_NAME=fs-api
SOURCE="$0"
while [ -h "$SOURCE"  ]; do
    DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
    SOURCE="$(readlink "$SOURCE")"
    [[ $SOURCE != /*  ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
WORKING_DIR="$(cd -P "$(dirname "$SOURCE")" && pwd)"
WORKING_HOME="$(dirname "$WORKING_DIR")"
cd "$(dirname "$0")" || exit 1
jarfile="$WORKING_HOME/lib/*.jar"
LOG_FOLDER="$WORKING_HOME/logs"
CONF_FOLDER="$WORKING_HOME/conf"
JAVA_OPTS="-Dfile.encoding=UTF-8 -XX:+UseG1GC -Xmn1g -Xmx2g -Xms2g"
RUN_ARGS="--spring.config.location=file:../conf/ --spring.profiles.active=nacos"

[[ -n "$APP_NAME" ]] && identity="$APP_NAME"
# Initialize log file name if not provided by the config file
[[ -z "$LOG_FILENAME" ]] && LOG_FILENAME="${identity}.log"

# Initialize PID/LOG locations if they weren't provided by the config file
[[ -z "$PID_FOLDER" ]] && PID_FOLDER="$WORKING_HOME"
[[ -z "$LOG_FOLDER" ]] && LOG_FOLDER="/var/log"

# Build the pid and log filenames
pid_file="$PID_FOLDER/${identity}.pid"
log_file="$LOG_FOLDER/$LOG_FILENAME"
conf_file="CONF_FOLDER/$LOG_FILENAME"

# Initialize stop wait time if not provided by the config file
[[ -z "$STOP_WAIT_TIME" ]] && STOP_WAIT_TIME="60"

# ANSI Colors
echoRed() { echo $'\e[0;31m'"$1"$'\e[0m'; }
echoGreen() { echo $'\e[0;32m'"$1"$'\e[0m'; }
echoYellow() { echo $'\e[0;33m'"$1"$'\e[0m'; }

# Find Java
if [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]]; then
    javaexe="$JAVA_HOME/bin/java"
elif type -p java > /dev/null 2>&1; then
    javaexe=$(type -p java)
elif [[ -x "/usr/bin/java" ]];  then
    javaexe="/usr/bin/java"
else
    echo "Unable to find Java"
    exit 1
fi

isRunning() {
  ps -p "$1" &> /dev/null
}


# Action functions
start() {
  if [[ -f "$pid_file" ]]; then
    pid=$(cat "$pid_file")
    isRunning "$pid" && { echoYellow "Already running [$pid]"; return 0; }
  fi
  do_start "$@"
}

do_start() {
  /bin/sh -c "$javaexe $JAVA_OPTS -jar $jarfile $RUN_ARGS > /dev/null 2>&1 & echo \$!" > "$pid_file"
  pid=$(cat "$pid_file")
  [[ -z $pid ]] && { echoRed "Failed to start"; return 1; }
  echoGreen "Started [$pid]"
}

stop(){
  [[ -f $pid_file ]] || { echoYellow "Not running (pidfile not found)"; return 0; }
  pid=$(cat "$pid_file")
  isRunning "$pid" || { echoYellow "Not running (process ${pid}). Removing stale pid file."; rm -f "$pid_file"; return 0; }
  do_stop "$pid" "$pid_file"
}

do_stop(){
  kill -9 "$1" &> /dev/null || { echoRed "Unable to kill process $1"; return 1; }
  for i in $(seq 1 $STOP_WAIT_TIME); do
    isRunning "$1" || { echoGreen "Stopped [$1]"; rm -f "$2"; return 0; }
    [[ $i -eq STOP_WAIT_TIME/2 ]] && kill "$1" &> /dev/null
    sleep 1
  done
  echoRed "Unable to kill process $1";
  return 1;
}

force_stop() {
  [[ -f $pid_file ]] || { echoYellow "Not running (pidfile not found)"; return 0; }
  pid=$(cat "$pid_file")
  isRunning "$pid" || { echoYellow "Not running (process ${pid}). Removing stale pid file."; rm -f "$pid_file"; return 0; }
  do_force_stop "$pid" "$pid_file"
}

do_force_stop() {
  kill -9 "$1" &> /dev/null || { echoRed "Unable to kill process $1"; return 1; }
  for i in $(seq 1 $STOP_WAIT_TIME); do
    isRunning "$1" || { echoGreen "Stopped [$1]"; rm -f "$2"; return 0; }
    [[ $i -eq STOP_WAIT_TIME/2 ]] && kill -9 "$1" &> /dev/null
    sleep 1
  done
  echoRed "Unable to kill process $1";
  return 1;
}

restart() {
  stop && start
}

force_reload() {
  [[ -f $pid_file ]] || { echoRed "Not running (pidfile not found)"; return 7; }
  pid=$(cat "$pid_file")
  rm -f "$pid_file"
  isRunning "$pid" || { echoRed "Not running (process ${pid} not found)"; return 7; }
  do_stop "$pid" "$pid_file"
  do_start
}

status() {
  [[ -f "$pid_file" ]] || { echoRed "Not running"; return 3; }
  pid=$(cat "$pid_file")
  isRunning "$pid" || { echoRed "Not running (process ${pid} not found)"; return 1; }
  echoGreen "Running [$pid]"
  return 0
}

run() {
  $javaexe $JAVA_OPTS -jar $jarfile $RUN_ARGS
  result=$?
  return "$result"
}

help() {
    echo "start 启动"
    echo "stop  停止"
    echo "status 当前运行状态"
    echo ""
}

# Determine the script mode
action="start"
[[ -n "$1" ]] && action="$1"

# Call the appropriate action function
case "$action" in
start)
  start "$@"; exit $?;;
stop)
  stop "$@"; exit $?;;
force-stop)
  force_stop "$@"; exit $?;;
restart)
  restart "$@"; exit $?;;
force-reload)
  force_reload "$@"; exit $?;;
status)
  status "$@"; exit $?;;
run)
  run "$@"; exit $?;;
*)
  echo "Usage: $0 {start|stop|force-stop|restart|force-reload|status|run}"; exit 1;
esac

exit 0