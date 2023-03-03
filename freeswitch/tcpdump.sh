#!/bin/sh

/usr/sbin/pcapsipdump -d /app/minio/data/html/  -i any -B 512MiB -x 10800 -y 150 -o 7600 -o 7610 -o 8600 -o 8610 -o 9600 -o 9610 -o 5080 -o 5060 -o 6685 -o 38880



