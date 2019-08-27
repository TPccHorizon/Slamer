FROM postgres:10.4
COPY ./init/setup.sh /docker-entrypoint-initdb.d/
COPY ./init/init.sql /docker-entrypoint-initdb.d/
WORKDIR /docker-entrypoint-initdb.d/
#RUN sleep 5
#RUN psql -f init.sql
#RUN setup.sh