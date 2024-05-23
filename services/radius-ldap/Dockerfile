FROM freeradius/freeradius-server:latest
COPY raddb/ /etc/raddb/
COPY tests/ /home/tests/
CMD ["freeradius", "-f"]