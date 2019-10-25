# base image
FROM node:12.4.0-alpine

# set working directory
WORKDIR /app

# install and cache app dependencies
COPY package.json /app/package.json
RUN npm install --silent
run npm install react-scripts -g --silent
COPY . .

EXPOSE 3000

RUN apk --no-cache add curl 

# start app
CMD ["npm", "start"]