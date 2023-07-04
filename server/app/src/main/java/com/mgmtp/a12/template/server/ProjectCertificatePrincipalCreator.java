package com.mgmtp.a12.template.server;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.naming.ldap.LdapName;

import org.slf4j.Logger;
import java.security.cert.X509Certificate;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.InvalidNameException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mgmtp.a12.uaa.authentication.certificate.CertificateConverter;
import com.mgmtp.a12.uaa.authentication.user.internal.UserProcessor;

@Component
public class ProjectCertificatePrincipalCreator implements CertificateConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCertificatePrincipalCreator.class);

    private static final String COMMON_NAME = "CN";
    private static final String USER_ROLE = "OID.1.2.3.4.5.6.7.9";

    @Inject
    private UserProcessor userProcessor;

    @Override
    public UserDetails convert(X509Certificate certificate) {
        LOGGER.info("Start to convert certificate");
        final String userName = getAttributeValueFromCertificate(certificate, COMMON_NAME);
        final String userRole = getAttributeValueFromCertificate(certificate, USER_ROLE);

        Set<GrantedAuthority> grantedAuthorities = Arrays.asList(userRole.split(";")).stream()
            .map(roleName -> new SimpleGrantedAuthority(roleName))
            .collect(Collectors.toSet());
        UserDetails user = userProcessor.createUser(userName, grantedAuthorities, certificate);
        LOGGER.info("User converted " + user);
        return user;

    }

    private String getAttributeValueFromCertificate(X509Certificate certificate, String attributeName) {
        try {
            String subjectName = certificate.getSubjectDN().getName();
            LdapName ln = new LdapName(subjectName);
            return ln.getRdns()
                .stream()
                .filter(rdn -> rdn.getType().equals(attributeName))
                .findFirst().get().getValue().toString();
        } catch (InvalidNameException | javax.naming.InvalidNameException e) {
            LOGGER.error("Has error when get attribute value from certificate subject name", e);
        }
        return null;
    }
}