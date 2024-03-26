FROM node:21 as base
WORKDIR /usr/src/app

FROM base AS install
RUN mkdir -p /temp/dev
COPY package.json yarn.lock /temp/dev/
RUN cd /temp/dev && yarn install

FROM base AS build
COPY --from=install /temp/dev/node_modules node_modules
COPY . .
RUN yarn run build

FROM nginx
COPY ./nginx/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /usr/src/app/dist /usr/share/nginx/html
EXPOSE 80
