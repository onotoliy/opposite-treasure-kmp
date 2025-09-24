FROM nginx:1.27-alpine
COPY --from=build /home/gradle/project/app/js/build/distributions /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]