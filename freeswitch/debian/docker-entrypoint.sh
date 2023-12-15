#!/bin/bash
set -e

#echo "restart nginx"
/etc/init.d/nginx  start

echo "start freeswitch"
cd /usr/local/freeswitch/bin

exec ./freeswitch  -nonat -c

exec "$@"
