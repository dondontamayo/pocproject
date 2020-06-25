package com.dondontamayo.poc.sslsecurity.config;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import com.dondontamayo.poc.sslsecurity.feign.ServerFeign;

import feign.Client;
import feign.Feign;
import lombok.extern.slf4j.Slf4j;

//@Configuration
@Slf4j
public class FeignClientConfig {
	
	@Bean
	public ServerFeign feignClient() {
		Client client = new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
		return Feign.builder().client(client)
		          .target(ServerFeign.class, "localhost:8081");
	}

	private SSLSocketFactory getSSLSocketFactory() {
		try(FileInputStream kStorefile = new FileInputStream("server1-keystore.jks")) {
			
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(kStorefile, "password1".toCharArray());
			TrustStrategy acceptingTrustStrategy = new TrustStrategy()  {

				@Override
				public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws java.security.cert.CertificateException {
					return true;
				}
			};
			
			SSLContext sslContext = SSLContextBuilder
                    .create()
                    .loadKeyMaterial(keyStore, "password1".toCharArray())
                    .loadTrustMaterial(ResourceUtils.getFile("classpath:server-truststore.jks"), "password".toCharArray())
                    .build();
            return sslContext.getSocketFactory();
		} catch (RuntimeException ex) {
		      log.info("{}", ex.getMessage());
		} catch (Exception ex) {
		      log.info("{}", ex.getMessage());
		}
	return null;
	}
}
