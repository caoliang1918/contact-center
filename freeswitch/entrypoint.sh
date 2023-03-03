#!/bin/bash

set -e

cd /usr/local/freeswitch/bin

freeswitch -nc -rp

exec "$@"
