FROM debian:bookworm
MAINTAINER caoliang1918 <caoliang@voice9.com>

WORKDIR /usr/local/freeswitch
ADD docker-entrypoint.sh /usr/local/
RUN chmod +x /usr/local/docker-entrypoint.sh

RUN echo voice9 >  /etc/hostname
RUN sed -i 's/http:\/\/deb.debian.org/http:\/\/mirrors.huaweicloud.com/g' /etc/apt/sources.list.d/debian.sources
RUN apt-get update && DEBIAN_FRONTEND=noninteractive apt-get -yq install vim git sngrep

RUN git clone -b 'v1.10.11' https://github.com/signalwire/freeswitch /usr/src/freeswitch
RUN git clone -b 'v2.0.3'   https://github.com/signalwire/libks /usr/src/libs/libks
RUN git clone -b 'v1.13.17' https://github.com/freeswitch/sofia-sip /usr/src/libs/sofia-sip
RUN git clone https://github.com/freeswitch/spandsp /usr/src/libs/spandsp && cd /usr/src/libs/spandsp  &&  git  checkout  0d2e6ac
RUN git clone -b 'v2.0.0'   https://github.com/signalwire/signalwire-c /usr/src/libs/signalwire-c

RUN DEBIAN_FRONTEND=noninteractive apt-get -yq install \
# nginx
   nginx \
# build
    build-essential cmake automake autoconf 'libtool-bin|libtool' pkg-config \
# general
    libssl-dev zlib1g-dev libdb-dev unixodbc-dev libncurses5-dev libexpat1-dev libgdbm-dev bison erlang-dev libtpl-dev libtiff5-dev uuid-dev \
# core
    libpcre3-dev libedit-dev libsqlite3-dev libcurl4-openssl-dev nasm \
# core codecs
    libogg-dev libspeex-dev libspeexdsp-dev \
# mod_enum
    libldns-dev \
# mod_python3
    python3-dev \
# mod_av
    libavformat-dev libswscale-dev libva-dev \
# mod_lua
    liblua5.2-dev \
# mod_opus
    libopus-dev \
# mod_pgsql
    libpq-dev \
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

RUN cd /usr/src/freeswitch && ./bootstrap.sh -j
RUN cd /usr/src/freeswitch && ./configure
RUN cd /usr/src/freeswitch && make -j`nproc` && make install

# Cleanup the image
RUN apt-get clean
RUN rm -rf /usr/src/*

ENTRYPOINT ["/usr/local/docker-entrypoint.sh"]
