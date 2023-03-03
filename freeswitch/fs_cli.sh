#!/bin/sh

docker exec -it freeswitch_x86  /usr/local/freeswitch/bin/fs_cli -H 172.17.0.2 -P 7400 -p voice9.com

