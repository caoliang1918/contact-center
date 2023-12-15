FROM centos:centos7.9.2009
MAINTAINER caoliang1918 <caoliang1918@gmail.com>

WORKDIR /usr/local/freeswitch
ADD docker-entrypoint.sh /usr/local/
RUN chmod +x /usr/local/docker-entrypoint.sh
RUN echo voice9 >  /etc/hostname
RUN yum install -y  wget


RUN wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
RUN yum clean all 
RUN yum makecache 
RUN yum -y update


RUN yum install -y git vim tar  autoconf automake libtool gcc-c++ ncurses-devel make
RUN yum install -y https://$(< /etc/yum/vars/signalwireusername):$(< /etc/yum/vars/signalwiretoken)@freeswitch.signalwire.com/repo/yum/centos-release/freeswitch-release-repo-0-1.noarch.rpm epel-release
RUN yum install -y libtiff-devel.x86_64 libpng12 unzip nasm
RUN yum install -y libshout-devel lame-devel libmpg123-devel
RUN yum install -y alsa-lib-devel bison broadvoice-devel bzip2 curl-devel
RUN yum install -y libdb4-devel  erlang  gcc-c++ gdbm-devel
RUN yum install -y gnutls-devel ilbc2-devel ldns-devel libcodec2-devel libcurl-devel libedit-devel
RUN yum install -y libidn-devel libjpeg-devel libmemcached-devel libogg-devel libsilk-devel
RUN yum install -y libsndfile-devel libtheora-devel libtiff-devel libtool libuuid-devel libvorbis-devel
RUN yum install -y libxml2-devel lua-devel lzo-devel mongo-c-driver-devel ncurses-devel net-snmp-devel
RUN yum install -y openssl-devel pcre-devel perl perl-ExtUtils-Embed pkgconfig portaudio-devel
RUN yum install -y postgresql-devel python-devel python-devel soundtouch-devel speex-devel sqlite-devel
RUN yum install -y unbound-devel unixODBC-devel which zlib-devel libshout-devel 
RUN yum install -y lame-devel readline-devel pcre pcre-devel openssl11 openssl11-devel opus-devel libatomic

# cmake编译
RUN yum remove cmake
RUN git clone  https://github.com/Kitware/CMake.git && cd CMake
RUN ./bootstrap && make && sudo make install

#libks编译
RUN cd /usr/src/
RUN git clone https://github.com/signalwire/libks.git && cd libks
RUN cmake .
RUN make && make install


RUN cd /usr/src  && git clone https://github.com/signalwire/signalwire-c.git
RUN cd  signalwire-c/
RUN cmake .
RUN make && make install
RUN ln -sf /usr/local/lib64/pkgconfig/signalwire_client.pc /usr/lib64/pkgconfig/signalwire_client.pc


#RUN export PKG_CONFIG_PATH=/usr/local/lib/pkgconfig:/usr/local/lib64/pkgconfig:${PKG_CONFIG_PATH}
RUN ldconfig

#源码下载
RUN git clone -b 'v1.10.10' https://github.com/signalwire/freeswitch /usr/src/freeswitch
RUN git clone -b 'v2.0.3'   https://github.com/signalwire/libks /usr/src/libs/libks
RUN git clone -b 'v1.13.17' https://github.com/freeswitch/sofia-sip /usr/src/libs/sofia-sip
RUN git clone https://github.com/freeswitch/spandsp /usr/src/libs/spandsp && cd /usr/src/libs/spandsp  &&  git  checkout  0d2e6ac
RUN git clone -b 'v2.0.0'   https://github.com/signalwire/signalwire-c /usr/src/libs/signalwire-c

RUN yum install -y nginx libavformat-dev libswscale-dev libavresample-dev \
# mod_lua
    liblua5.2-dev \
# mod_opus
    libopus-dev \
# mod_sndfile
    libsndfile1-dev libflac-dev libogg-dev libvorbis-dev \
# mod_shout
    libshout3-dev libmpg123-dev libmp3lame-dev

RUN cd /usr/src/libs/libks && cmake . -DCMAKE_INSTALL_PREFIX=/usr -DWITH_LIBBACKTRACE=1 && make install
RUN cd /usr/src/libs/sofia-sip && ./bootstrap.sh && ./configure CFLAGS="-g -ggdb" --with-pic --with-glib=no --without-doxygen --disable-stun --prefix=/usr && make -j`nproc --all` && make install
RUN cd /usr/src/libs/spandsp  && ./bootstrap.sh && ./configure CFLAGS="-g -ggdb" --with-pic --prefix=/usr && make -j`nproc --all` && make install
RUN cd /usr/src/libs/signalwire-c && PKG_CONFIG_PATH=/usr/lib/pkgconfig cmake . -DCMAKE_INSTALL_PREFIX=/usr && make install

# Enable modules
RUN sed -i 's|#formats/mod_shout|formats/mod_shout|' /usr/src/freeswitch/build/modules.conf.in
RUN sed -i 's/applications\/mod_av/#applications\/mod_av/g'  /usr/src/freeswitch/build/modules.conf.in
RUN sed -i 's/applications\/mod_signalwire/#applications\/mod_signalwire/g' /usr/src/freeswitch/build/modules.conf.in
RUN sed -i 's/endpoints\/mod_verto/#endpoints\/mod_verto/g' /usr/src/freeswitch/build/modules.conf.in
RUN sed -i 's/codecs\/mod_opus/#codecs\/mod_opus/g' /usr/src/freeswitch/build/modules.conf.in

RUN cd /usr/src/freeswitch && ./bootstrap.sh -j
RUN cd /usr/src/freeswitch && ./configure
RUN cd /usr/src/freeswitch && make -j`nproc` && make install

# Cleanup the image
RUN rm -rf /usr/src/*

ENTRYPOINT ["/usr/local/docker-entrypoint.sh"]
