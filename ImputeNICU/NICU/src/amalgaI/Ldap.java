package amalgaI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
* Provides simplified access to Mayo's LDAP directory. There are three main
* methods provided by this utility. These are <code>connect</code>,
* <code>getGroupsForUser</code> and <code>getUsersForGroup</code>.
*
* <h2>Justification</h2>
* <p>
* Centralizes the LDAP access and configuration for accessing Mayo's LDAP
* directory.
* </p>
*/
public class Ldap {
      public static String MAYO_LDAP_URL = "ldap://mfadldap.mfad.mfroot.org:389";
      public static String MAYO_USER_DOMAIN = "mfad.mfroot.org";
      public static String MAYO_CONTEXT_NAME = "DC=mfad,DC=mfroot,DC=org";

      /**
      * Connects to Mayo's LDAP server.
      * @param lanId Lan ID used to access LDAP.
      * @param password LDAP password.
      * @return Directory context for the server.
      * @throws NamingException
      */
      public static DirContext connect(String lanId, String password) throws NamingException {
            String ldapUsername = lanId.toUpperCase() + "@" + MAYO_USER_DOMAIN;

            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, MAYO_LDAP_URL);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
            env.put(Context.SECURITY_CREDENTIALS, password);
            env.put(Context.REFERRAL, "follow");

            return new InitialDirContext(env);
      }

      /**
      * Retrieves a mapping of user properties (id to value) for the specified user.
      * @param ldapLanId Lan ID used to access LDAP.
      * @param ldapPassword LDAP password.
      * @param lanId Lan ID of the user to retrieve the properties of.
      * @return Mapping of user properties (id to value).
      * @throws NamingException
      */
      public static Map<String, String> getUserProperties(String ldapLanId, String ldapPassword, String lanId) throws NamingException {
            DirContext ctxt = connect(ldapLanId, ldapPassword);
            try {
                  return getUserProperties(ctxt, lanId);
            }
            finally {
                  ctxt.close();
            }
      }

      /**
      * Retrieves a mapping of user properties (id to value) for the specified user.
      * @param context LDAP directory context.
      * @param lanId Lan ID of the user to retrieve the properties of.
      * @return Mapping of user properties (id to value).
      * @throws NamingException
      */
      public static Map<String, String> getUserProperties(DirContext context, String lanId) throws NamingException {
            Map<String, String> m = new HashMap<String, String>();
        String query = "(&(objectclass=user)(cn=" + lanId.toUpperCase() + "))";

        SearchControls ctrl = new SearchControls();
        ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> enumeration = context.search(MAYO_CONTEXT_NAME, query, ctrl);
        while (enumeration.hasMore()) {
            SearchResult result = (SearchResult) enumeration.next();
            Attributes attribs = result.getAttributes();

            NamingEnumeration<? extends Attribute> all = attribs.getAll();
            while( all.hasMore() ) {
                  Attribute a = all.next();
                  NamingEnumeration<?> values = a.getAll();

                  StringBuilder sb = new StringBuilder();
                  while( values.hasMore() ) {
                        if( sb.length() > 0 ) sb.append(";");
                        sb.append(values.next().toString());
                  }

                  m.put(a.getID(), sb.toString());
            }
        }

        return m;
      }

      /**
      * Retrieves a list of groups that a user belongs to. Retrieves only the
      * common name value for the group not the full distinguished name.
      * @param ldapLanId Lan ID used to access LDAP.
      * @param ldapPassword LDAP password.
      * @param lanId Lan ID of the user to retrieve the groups for.
      * @return List of the group names.
      * @throws NamingException
      */
      public static List<String> getGroupsForUser(String ldapLanId, String ldapPassword, String lanId) throws NamingException {
            DirContext ctxt = connect(ldapLanId, ldapPassword);
            try {
                  return getGroupsForUser(ctxt, lanId);
            }
            finally {
                  ctxt.close();
            }
      }

      /**
      * Retrieves a list of groups that a user belongs to. Retrieves only the
      * common name value for the group not the full distinguished name.
      * @param context LDAP directory context.
      * @param lanId Lan ID of the user to retrieve the groups for.
      * @return List of the group names.
      * @throws NamingException
      */
      public static List<String> getGroupsForUser(DirContext context, String lanId) throws NamingException {
            List<String> groups = new ArrayList<String>();
        String query = "(&(objectclass=user)(cn=" + lanId.toUpperCase() + "))";

        SearchControls ctrl = new SearchControls();
        ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> enumeration = context.search(MAYO_CONTEXT_NAME, query, ctrl);
        while (enumeration.hasMore()) {
            SearchResult result = enumeration.next();
            Attributes attribs = result.getAttributes();

            NamingEnumeration<?> values = ((BasicAttribute) attribs.get("memberOf")).getAll();
            while (values.hasMore()) {
                  String groupName = values.next().toString();

                  // Group name has the format: cn=Group Name,other values...
                  int posEquals = groupName.indexOf('=');
                  int posComma = groupName.indexOf(',');
                  if( posEquals>=0 && posComma>=0 && posEquals<posComma ) {
                        groupName = groupName.substring(posEquals+1, posComma);
                  }

                  groups.add(groupName);
            }
        }
        return groups;
      }

      /**
      * Retrieves a list of users that belong to the specified group. Retrieves
      * only the common name for the user (lan ID) not the full distiguished name.
      * @param ldapLanId Lan ID used to access LDAP.
      * @param ldapPassword LDAP password.
      * @param group Name of the group to retrieve the user list from.
      * @return List of lan IDs.
      * @throws NamingException
      */
      public static List<String> getUsersForGroup(String ldapLanId, String ldapPassword, String group) throws NamingException {
            DirContext ctxt = connect(ldapLanId, ldapPassword);
            try {
                  return getUsersForGroup(ctxt, group);
            }
            finally {
                  ctxt.close();
            }
      }

      /**
      * Retrieves a list of users that belong to the specified group. Retrieves
      * only the common name for the user (lan ID) not the full distiguished name.
      * @param context LDAP directory context.
      * @param group Name of the group to retrieve the user list from.
      * @return List of lan IDs.
      * @throws NamingException
      */
      public static List<String> getUsersForGroup(DirContext context, String group) throws NamingException {
            List<String> users = new ArrayList<String>();
        String query = "(&(objectclass=group)(cn=" + group + "))";

        SearchControls ctrl = new SearchControls();
        ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> enumeration = context.search(MAYO_CONTEXT_NAME, query, ctrl);
        while (enumeration.hasMore()) {
            SearchResult result = enumeration.next();
            Attributes attribs = result.getAttributes();

            NamingEnumeration<?> values = ((BasicAttribute) attribs.get("member")).getAll();
            while (values.hasMore()) {
                  String userName = values.next().toString();

                  // User name has the format: cn=User Name,other values...
                  int posEquals = userName.indexOf('=');
                  int posComma = userName.indexOf(',');
                  if( posEquals>=0 && posComma>=0 && posEquals<posComma ) {
                        userName = userName.substring(posEquals+1, posComma);
                  }

                  users.add(userName);
            }
        }
            return users;
      }

}
