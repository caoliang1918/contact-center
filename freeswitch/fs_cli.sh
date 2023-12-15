#!/bin/sh

docker exec -it freeswitch  /usr/local/freeswitch/bin/fs_cli -H 172.17.0.2 -P 8021 -p ClueCon

