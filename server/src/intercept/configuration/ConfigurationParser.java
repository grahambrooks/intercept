// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g 2010-09-24 20:23:13

package intercept.configuration;


import org.antlr.runtime.BitSet;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class ConfigurationParser extends Parser {
    public static final String[] tokenNames = new String[]{
            "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PORT", "EQ", "NUMBER", "PROXY", "IDENTIFIER", "BEGIN", "END", "ROUTE", "STRING", "OUTPROXY", "DEBUG", "STUB", "HOST", "RESPONSE", "HEADER", "BLOCK", "BODY", "URL", "OPENBLOCK", "CLOSEBLOCK", "WS", "'=>'"
    };
    public static final int PROXY = 7;
    public static final int ROUTE = 11;
    public static final int T__25 = 25;
    public static final int NUMBER = 6;
    public static final int HOST = 16;
    public static final int PORT = 4;
    public static final int CLOSEBLOCK = 23;
    public static final int EOF = -1;
    public static final int URL = 21;
    public static final int OPENBLOCK = 22;
    public static final int DEBUG = 14;
    public static final int WS = 24;
    public static final int IDENTIFIER = 8;
    public static final int BLOCK = 19;
    public static final int BEGIN = 9;
    public static final int OUTPROXY = 13;
    public static final int STUB = 15;
    public static final int BODY = 20;
    public static final int EQ = 5;
    public static final int RESPONSE = 17;
    public static final int HEADER = 18;
    public static final int END = 10;
    public static final int STRING = 12;

    // delegates
    // delegators


    public ConfigurationParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public ConfigurationParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);

    }


    public String[] getTokenNames() {
        return ConfigurationParser.tokenNames;
    }

    public String getGrammarFileName() {
        return "/Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g";
    }


    private String trimBrackets(String response) {
        return response.substring(1, response.length() - 1);
    }


    // $ANTLR start "configuration"
    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:17:1: configuration[InterceptConfiguration config] : ( configurationStatement[config] )* EOF ;
    public final void configuration(InterceptConfiguration config) throws RecognitionException {
        try {
            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:18:2: ( ( configurationStatement[config] )* EOF )
            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:18:4: ( configurationStatement[config] )* EOF
            {
                // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:18:4: ( configurationStatement[config] )*
                loop1:
                do {
                    int alt1 = 2;
                    int LA1_0 = input.LA(1);

                    if ((LA1_0 == PORT || LA1_0 == PROXY)) {
                        alt1 = 1;
                    }


                    switch (alt1) {
                        case 1:
                            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:18:5: configurationStatement[config]
                        {
                            pushFollow(FOLLOW_configurationStatement_in_configuration34);
                            configurationStatement(config);

                            state._fsp--;


                        }
                        break;

                        default:
                            break loop1;
                    }
                } while (true);

                match(input, EOF, FOLLOW_EOF_in_configuration40);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return;
    }
    // $ANTLR end "configuration"


    // $ANTLR start "configurationStatement"
    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:21:1: configurationStatement[InterceptConfiguration config] : ( PORT EQ port= NUMBER | def= proxyDefinition );
    public final void configurationStatement(InterceptConfiguration config) throws RecognitionException {
        Token port = null;
        ProxyConfig def = null;


        try {
            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:22:2: ( PORT EQ port= NUMBER | def= proxyDefinition )
            int alt2 = 2;
            int LA2_0 = input.LA(1);

            if ((LA2_0 == PORT)) {
                alt2 = 1;
            } else if ((LA2_0 == PROXY)) {
                alt2 = 2;
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1:
                    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:22:4: PORT EQ port= NUMBER
                {
                    match(input, PORT, FOLLOW_PORT_in_configurationStatement51);
                    match(input, EQ, FOLLOW_EQ_in_configurationStatement53);
                    port = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_configurationStatement57);
                    config.setConfigurationPort(Integer.parseInt(port.getText()));

                }
                break;
                case 2:
                    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:23:4: def= proxyDefinition
                {
                    pushFollow(FOLLOW_proxyDefinition_in_configurationStatement66);
                    def = proxyDefinition();

                    state._fsp--;

                    config.add(def);

                }
                break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return;
    }
    // $ANTLR end "configurationStatement"


    // $ANTLR start "proxyDefinition"
    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:26:1: proxyDefinition returns [ProxyConfig proxy] : PROXY name= IDENTIFIER BEGIN ( proxyStatement[proxy] )* END ;
    public final ProxyConfig proxyDefinition() throws RecognitionException {
        ProxyConfig proxy = null;

        Token name = null;

        proxy = new DefaultProxyConfig();
        try {
            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:28:2: ( PROXY name= IDENTIFIER BEGIN ( proxyStatement[proxy] )* END )
            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:28:4: PROXY name= IDENTIFIER BEGIN ( proxyStatement[proxy] )* END
            {
                match(input, PROXY, FOLLOW_PROXY_in_proxyDefinition87);
                name = (Token) match(input, IDENTIFIER, FOLLOW_IDENTIFIER_in_proxyDefinition91);
                proxy.setName(name.getText());
                match(input, BEGIN, FOLLOW_BEGIN_in_proxyDefinition94);
                // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:28:66: ( proxyStatement[proxy] )*
                loop3:
                do {
                    int alt3 = 2;
                    int LA3_0 = input.LA(1);

                    if ((LA3_0 == PORT || LA3_0 == ROUTE || (LA3_0 >= OUTPROXY && LA3_0 <= STUB))) {
                        alt3 = 1;
                    }


                    switch (alt3) {
                        case 1:
                            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:28:67: proxyStatement[proxy]
                        {
                            pushFollow(FOLLOW_proxyStatement_in_proxyDefinition97);
                            proxyStatement(proxy);

                            state._fsp--;


                        }
                        break;

                        default:
                            break loop3;
                    }
                } while (true);

                match(input, END, FOLLOW_END_in_proxyDefinition102);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return proxy;
    }
    // $ANTLR end "proxyDefinition"


    // $ANTLR start "proxyStatement"
    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:31:1: proxyStatement[ProxyConfig proxy] : ( PORT '=' port= NUMBER | ROUTE from= STRING '=>' route= STRING | OUTPROXY '=' outproxy= STRING | statement= stubStatement | DEBUG EQ debugLevel= NUMBER );
    public final void proxyStatement(ProxyConfig proxy) throws RecognitionException {
        Token port = null;
        Token from = null;
        Token route = null;
        Token outproxy = null;
        Token debugLevel = null;
        StubResponse statement = null;


        try {
            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:32:2: ( PORT '=' port= NUMBER | ROUTE from= STRING '=>' route= STRING | OUTPROXY '=' outproxy= STRING | statement= stubStatement | DEBUG EQ debugLevel= NUMBER )
            int alt4 = 5;
            switch (input.LA(1)) {
                case PORT: {
                    alt4 = 1;
                }
                break;
                case ROUTE: {
                    alt4 = 2;
                }
                break;
                case OUTPROXY: {
                    alt4 = 3;
                }
                break;
                case STUB: {
                    alt4 = 4;
                }
                break;
                case DEBUG: {
                    alt4 = 5;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 4, 0, input);

                    throw nvae;
            }

            switch (alt4) {
                case 1:
                    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:32:4: PORT '=' port= NUMBER
                {
                    match(input, PORT, FOLLOW_PORT_in_proxyStatement115);
                    match(input, EQ, FOLLOW_EQ_in_proxyStatement117);
                    port = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_proxyStatement121);
                    proxy.setPort(Integer.parseInt(port.getText()));

                }
                break;
                case 2:
                    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:33:4: ROUTE from= STRING '=>' route= STRING
                {
                    match(input, ROUTE, FOLLOW_ROUTE_in_proxyStatement128);
                    from = (Token) match(input, STRING, FOLLOW_STRING_in_proxyStatement132);
                    match(input, 25, FOLLOW_25_in_proxyStatement134);
                    route = (Token) match(input, STRING, FOLLOW_STRING_in_proxyStatement138);
                    proxy.addRoute(from.getText(), route.getText());

                }
                break;
                case 3:
                    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:34:4: OUTPROXY '=' outproxy= STRING
                {
                    match(input, OUTPROXY, FOLLOW_OUTPROXY_in_proxyStatement145);
                    match(input, EQ, FOLLOW_EQ_in_proxyStatement147);
                    outproxy = (Token) match(input, STRING, FOLLOW_STRING_in_proxyStatement151);
                    proxy.setOutgoingProxy(outproxy.getText());

                }
                break;
                case 4:
                    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:35:4: statement= stubStatement
                {
                    pushFollow(FOLLOW_stubStatement_in_proxyStatement160);
                    statement = stubStatement();

                    state._fsp--;

                    proxy.add(statement);

                }
                break;
                case 5:
                    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:36:4: DEBUG EQ debugLevel= NUMBER
                {
                    match(input, DEBUG, FOLLOW_DEBUG_in_proxyStatement167);
                    match(input, EQ, FOLLOW_EQ_in_proxyStatement169);
                    debugLevel = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_proxyStatement173);
                    proxy.setLogLevel(Integer.parseInt(debugLevel.getText()));

                }
                break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return;
    }
    // $ANTLR end "proxyStatement"


    // $ANTLR start "stubStatement"
    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:39:1: stubStatement returns [StubResponse stub] : STUB url= HOST BEGIN ( stubSetting[stub] )* END ;
    public final StubResponse stubStatement() throws RecognitionException {
        StubResponse stub = null;

        Token url = null;


        stub = new StubResponse();

        try {
            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:43:2: ( STUB url= HOST BEGIN ( stubSetting[stub] )* END )
            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:43:4: STUB url= HOST BEGIN ( stubSetting[stub] )* END
            {
                match(input, STUB, FOLLOW_STUB_in_stubStatement195);
                url = (Token) match(input, HOST, FOLLOW_HOST_in_stubStatement199);
                stub.setUrl(url.getText());
                match(input, BEGIN, FOLLOW_BEGIN_in_stubStatement202);
                // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:44:3: ( stubSetting[stub] )*
                loop5:
                do {
                    int alt5 = 2;
                    int LA5_0 = input.LA(1);

                    if (((LA5_0 >= RESPONSE && LA5_0 <= HEADER) || LA5_0 == BODY)) {
                        alt5 = 1;
                    }


                    switch (alt5) {
                        case 1:
                            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:44:4: stubSetting[stub]
                        {
                            pushFollow(FOLLOW_stubSetting_in_stubStatement207);
                            stubSetting(stub);

                            state._fsp--;


                        }
                        break;

                        default:
                            break loop5;
                    }
                } while (true);

                match(input, END, FOLLOW_END_in_stubStatement214);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return stub;
    }
    // $ANTLR end "stubStatement"


    // $ANTLR start "stubSetting"
    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:48:1: stubSetting[StubResponse stub] : ( RESPONSE EQ responseCode= NUMBER | HEADER EQ header= BLOCK | BODY EQ body= BLOCK );
    public final void stubSetting(StubResponse stub) throws RecognitionException {
        Token responseCode = null;
        Token header = null;
        Token body = null;

        try {
            // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:49:2: ( RESPONSE EQ responseCode= NUMBER | HEADER EQ header= BLOCK | BODY EQ body= BLOCK )
            int alt6 = 3;
            switch (input.LA(1)) {
                case RESPONSE: {
                    alt6 = 1;
                }
                break;
                case HEADER: {
                    alt6 = 2;
                }
                break;
                case BODY: {
                    alt6 = 3;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 6, 0, input);

                    throw nvae;
            }

            switch (alt6) {
                case 1:
                    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:49:4: RESPONSE EQ responseCode= NUMBER
                {
                    match(input, RESPONSE, FOLLOW_RESPONSE_in_stubSetting226);
                    match(input, EQ, FOLLOW_EQ_in_stubSetting228);
                    responseCode = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_stubSetting232);
                    stub.setResponseCode(responseCode.getText());

                }
                break;
                case 2:
                    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:50:4: HEADER EQ header= BLOCK
                {
                    match(input, HEADER, FOLLOW_HEADER_in_stubSetting239);
                    match(input, EQ, FOLLOW_EQ_in_stubSetting241);
                    header = (Token) match(input, BLOCK, FOLLOW_BLOCK_in_stubSetting245);
                    stub.setHeader(trimBrackets(header.getText()));

                }
                break;
                case 3:
                    // /Users/gcb/projects/intercept/server/src/intercept/configuration/Configuration.g:51:4: BODY EQ body= BLOCK
                {
                    match(input, BODY, FOLLOW_BODY_in_stubSetting252);
                    match(input, EQ, FOLLOW_EQ_in_stubSetting254);
                    body = (Token) match(input, BLOCK, FOLLOW_BLOCK_in_stubSetting258);
                    stub.setBody(trimBrackets(body.getText()));

                }
                break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }
        finally {
        }
        return;
    }
    // $ANTLR end "stubSetting"

    // Delegated rules


    public static final BitSet FOLLOW_configurationStatement_in_configuration34 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_EOF_in_configuration40 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PORT_in_configurationStatement51 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_EQ_in_configurationStatement53 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_configurationStatement57 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_proxyDefinition_in_configurationStatement66 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROXY_in_proxyDefinition87 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_IDENTIFIER_in_proxyDefinition91 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_BEGIN_in_proxyDefinition94 = new BitSet(new long[]{0x000000000000EC10L});
    public static final BitSet FOLLOW_proxyStatement_in_proxyDefinition97 = new BitSet(new long[]{0x000000000000EC10L});
    public static final BitSet FOLLOW_END_in_proxyDefinition102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PORT_in_proxyStatement115 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_EQ_in_proxyStatement117 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_proxyStatement121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROUTE_in_proxyStatement128 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_STRING_in_proxyStatement132 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_proxyStatement134 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_STRING_in_proxyStatement138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OUTPROXY_in_proxyStatement145 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_EQ_in_proxyStatement147 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_STRING_in_proxyStatement151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stubStatement_in_proxyStatement160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEBUG_in_proxyStatement167 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_EQ_in_proxyStatement169 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_proxyStatement173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STUB_in_stubStatement195 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_HOST_in_stubStatement199 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_BEGIN_in_stubStatement202 = new BitSet(new long[]{0x0000000000160400L});
    public static final BitSet FOLLOW_stubSetting_in_stubStatement207 = new BitSet(new long[]{0x0000000000160400L});
    public static final BitSet FOLLOW_END_in_stubStatement214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RESPONSE_in_stubSetting226 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_EQ_in_stubSetting228 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_NUMBER_in_stubSetting232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEADER_in_stubSetting239 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_EQ_in_stubSetting241 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_BLOCK_in_stubSetting245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BODY_in_stubSetting252 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_EQ_in_stubSetting254 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_BLOCK_in_stubSetting258 = new BitSet(new long[]{0x0000000000000002L});

}