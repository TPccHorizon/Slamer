FROM node:8-alpine
WORKDIR /app
COPY package.json package-lock.json  /app/
RUN npm install @angular/cli@7.3.8 -g
RUN cd /app && npm install
COPY . /app
#RUN npm rebuild node-sass
RUN cd /app && npm run build

FROM nginx:alpine
RUN rm -rf /usr/share/nginx/html/*
WORKDIR /usr/share/nginx/html
#RUN chown nginx:nginx ./*
WORKDIR /app
COPY nginx-default.conf /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx.conf
#RUN chmod -R g+rwX /docker/nginx/nginx-persistence
WORKDIR /usr/share/nginx/html
COPY dist/frontend/ .
