package com.mgmtp.a12.template.server;

import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;

import javax.naming.ldap.LdapName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.InvalidNameException;
import org.springframework.security.core.GrantedAuthority;

import com.mgmtp.a12.uaa.authentication.user.AbstractExtendedUser;
import com.mgmtp.a12.uaa.authentication.user.ExtendedUser;
import com.mgmtp.a12.uaa.authentication.user.Role;
import com.mgmtp.a12.uaa.authentication.user.UserFactory;

public class CustomUserFactory implements UserFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserFactory.class);

    private static final String USER_ROLE = "OID.1.2.3.4.5.6.7.9";

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractExtendedUser<?>> T createUser(String userName, Collection<? extends GrantedAuthority> authorities) {
        return (T) createUser(userName, "***", authorities, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractExtendedUser<?>> T createUser(String userName, String password, Collection<? extends GrantedAuthority> authorities,
        Object certificate) {
        authorities = createAuthorities(userName, null);
        AbstractExtendedUser user = new ExtendedUser(userName, password, authorities, certificate);
        return (T) user;
    }

    private Collection<? extends GrantedAuthority> createAuthorities(String username, X509Certificate certificate) {
        final String userRole = getAttributeValueFromCertificate(certificate, USER_ROLE);
        return Arrays.asList(new Role.Builder("admin").build());

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
