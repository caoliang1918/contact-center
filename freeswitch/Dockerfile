FROM registry.cn-hangzhou.aliyuncs.com/voice9_x86/freeswitch:1.0.0
ENV TZ=Asia/Shanghai
ADD entrypoint.sh /usr/local/freeswitch
ADD tcpdump.sh  /usr/local/freeswitch
WORKDIR /usr/local/freeswitch
ENTRYPOINT ["/usr/local/freeswitch/entrypoint.sh"]
CMD ["sh"]
