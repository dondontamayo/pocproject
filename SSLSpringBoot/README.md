# pocproject
1. SSL Mutual Authentication - generate using keytool

	- keytool -genkey -alias bmtamayo1 -storetype PKCS12 -keyalg RSA -keysize  2048 -keystore server1-keystore.jks -validity 3650
	- keytool -export -alias bmtamayo1 -file client1-public.cer -keystore server1-keystore.jks -deststoretype PKCS12
	- keytool -import -v -trustcacerts -alias bmtamayo1 -file client-public.cer -keystore truststore.ts
	
2. Generate self signed CA cert using OpenSSl

	- winpty openssl req -x509 -days 3650 -newkey rsa:2048 -keyout tamayo-cakey.pem -out tamayo-cacert.pem
	- winpty opensll -x509 in tamayo-cert.pem -out -text

3. Generate Server Certificate Request for Server1 and Server 2

	- winpty openssl req -newkey rsa:2048 -keyout server1-key.pem -out server1-req-cert.pem
	- winpty opensll req -newkey rsa:2048 -keyout server2-key.pem -out server2-req-cert.pem

4. Signed the Servers certificate using CA cert and create public cert for client

	- winpty openssl x509 -req -in server1-req-cert.pem -days 365 -CA tamayo-cacert.pem -CAkey tamayo-cakey.pem -CAcreateserial -out server1-cert.pem -extfile server-ext.cnf
	- winpty openssl x509 -req -in server2-req-cert.pem -days 365 -CA tamayo-cacert.pem -CAkey tamayo-cakey.pem -CAcreateserial -out server2-cert.pem -extfile server-ext.cnf
	
5. Export the server certs to pfx and create public key for client
	
	- winpty openssl pkcs12 -export -in server1-cert.pem -inkey server1-key.pem -out server1-keystore.pfx
	- winpty openssl pkcs12 -in server1-cert.pem -clcerts -nokeys -out server1-keystore-public.pem 
	- winpty openssl pkcs12 -export -in server2-cert.pem -inkey server2-key.pem out server2-keystore.pfx
	- winpty openssl pkcs12 -in server2-cert.pem -clcerts -nokeys -out server2-keystore-public.pem 
	
6. Generate a keystore (convert to pcks12)

	- winpty openssl pkcs12 -export -in server1-cert.pem -inkey server1-key.pem -out server1-keystore.p12 -name "server1"
	- keytool -importkeystore -destkeystore server1-keystore.jks -deststoretype PKCS12 -deststorepass password1 -srckeystore server1-keystore.p12 -srcstoretype PKCS12 -srcstorepass server1
	- keytool -import  -alias cacert -file tamayo-cacert.pem -keypass tamayo -keystore server1-keystore.jks -storepass password1
	
	- winpty openssl pkcs12 -export -in server2-cert.pem -inkey server2-key.pem -out server2-keystore.p12 -name "server2"
	- keytool -importkeystore -destkeystore server2-keystore.jks -deststoretype PKCS12 -deststorepass password2 -srckeystore server2-keystore.p12 -srcstoretype PKCS12 -srcstorepass password2
	- keytool -import  -alias cacert -file tamayo-cacert.pem -keypass tamayo -keystore server2-keystore.jks -storepass password2

7. Generate public cert for trust store

	- keytool -export -alias server1 -file server1-truststore.cer -keystore server1-keystore.jks
	- keytool -export -alias server2 -file server2-truststore.cer -keystore server1-keystore.jks
	
8. Generate a truststore for server

	- keytool -import -trustcacerts -alias cacert -file tamayo-cacert.pem -keystore server-truststore.jks -storepass password -noprompt
	- keytool -import -trustcacerts -alias server1 -file server1-truststore.cer -keystore server-truststore.jks -storepass password -noprompt
	- keytool -import -trustcacerts -alias server2 -file server2-truststore.cer -keystore server-truststore.jks -storepass password -noprompt