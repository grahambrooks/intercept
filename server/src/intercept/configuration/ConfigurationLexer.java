// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 C:\\development\\middleman\\trunk\\src\\middleman\\configuration\\Configuration.g 2009-08-27 15:52:36

package intercept.configuration;


import org.antlr.runtime.*;

public class ConfigurationLexer extends Lexer {
    public static final int PROXY=7;
    public static final int ROUTE=11;
    public static final int T__25=25;
    public static final int NUMBER=6;
    public static final int HOST=16;
    public static final int PORT=4;
    public static final int CLOSEBLOCK=23;
    public static final int EOF=-1;
    public static final int URL=21;
    public static final int OPENBLOCK=22;
    public static final int DEBUG=14;
    public static final int WS=24;
    public static final int IDENTIFIER=8;
    public static final int BLOCK=19;
    public static final int BEGIN=9;
    public static final int OUTPROXY=13;
    public static final int STUB=15;
    public static final int BODY=20;
    public static final int HEADER=18;
    public static final int RESPONSE=17;
    public static final int EQ=5;
    public static final int END=10;
    public static final int STRING=12;

    // delegates
    // delegators

    public ConfigurationLexer() {;} 
    public ConfigurationLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ConfigurationLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g"; }

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:7:7: ( '=>' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:7:9: '=>'
            {
            match("=>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "PROXY"
    public final void mPROXY() throws RecognitionException {
        try {
            int _type = PROXY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:48:7: ( 'proxy' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:48:9: 'proxy'
            {
            match("proxy"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PROXY"

    // $ANTLR start "ROUTE"
    public final void mROUTE() throws RecognitionException {
        try {
            int _type = ROUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:49:7: ( 'route' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:49:9: 'route'
            {
            match("route"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ROUTE"

    // $ANTLR start "BEGIN"
    public final void mBEGIN() throws RecognitionException {
        try {
            int _type = BEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:50:7: ( '{' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:50:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BEGIN"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:51:5: ( '}' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:51:7: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "STUB"
    public final void mSTUB() throws RecognitionException {
        try {
            int _type = STUB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:52:6: ( 'stub' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:52:8: 'stub'
            {
            match("stub"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STUB"

    // $ANTLR start "PORT"
    public final void mPORT() throws RecognitionException {
        try {
            int _type = PORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:53:6: ( 'port' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:53:8: 'port'
            {
            match("port"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PORT"

    // $ANTLR start "RESPONSE"
    public final void mRESPONSE() throws RecognitionException {
        try {
            int _type = RESPONSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:54:9: ( 'response' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:54:11: 'response'
            {
            match("response"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RESPONSE"

    // $ANTLR start "HEADER"
    public final void mHEADER() throws RecognitionException {
        try {
            int _type = HEADER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:55:8: ( 'header' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:55:10: 'header'
            {
            match("header"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HEADER"

    // $ANTLR start "BODY"
    public final void mBODY() throws RecognitionException {
        try {
            int _type = BODY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:56:6: ( 'body' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:56:8: 'body'
            {
            match("body"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BODY"

    // $ANTLR start "OUTPROXY"
    public final void mOUTPROXY() throws RecognitionException {
        try {
            int _type = OUTPROXY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:57:9: ( 'outgoing-proxy' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:57:11: 'outgoing-proxy'
            {
            match("outgoing-proxy"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OUTPROXY"

    // $ANTLR start "DEBUG"
    public final void mDEBUG() throws RecognitionException {
        try {
            int _type = DEBUG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:58:7: ( 'debug' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:58:9: 'debug'
            {
            match("debug"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEBUG"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:60:4: ( '=' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:60:6: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:64:2: ( ( '0' .. '9' )+ )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:64:4: ( '0' .. '9' )+
            {
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:64:4: ( '0' .. '9' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:64:5: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:68:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+ )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:68:4: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+
            {
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:68:4: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:
            	    {
            	    if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:70:8: ( '\"' ( '\\\\' . | ~ ( '\\\\' | '\"' ) )* '\"' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:70:10: '\"' ( '\\\\' . | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:70:14: ( '\\\\' . | ~ ( '\\\\' | '\"' ) )*
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='\\') ) {
                    alt3=1;
                }
                else if ( ((LA3_0>='\u0000' && LA3_0<='!')||(LA3_0>='#' && LA3_0<='[')||(LA3_0>=']' && LA3_0<='\uFFFF')) ) {
                    alt3=2;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:70:16: '\\\\' .
            	    {
            	    match('\\'); 
            	    matchAny(); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:70:25: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "URL"
    public final void mURL() throws RecognitionException {
        try {
            int _type = URL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:73:5: ( 'http://' ( 'a' .. 'z' | '0' .. '9' | '_' | '-' | '\\.' | ':' )+ )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:73:7: 'http://' ( 'a' .. 'z' | '0' .. '9' | '_' | '-' | '\\.' | ':' )+
            {
            match("http://"); 

            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:73:17: ( 'a' .. 'z' | '0' .. '9' | '_' | '-' | '\\.' | ':' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='-' && LA4_0<='.')||(LA4_0>='0' && LA4_0<=':')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:
            	    {
            	    if ( (input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<=':')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "URL"

    // $ANTLR start "HOST"
    public final void mHOST() throws RecognitionException {
        try {
            int _type = HOST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:76:6: ( ( 'a' .. 'z' | '0' .. '9' | '_' | '-' | '\\.' | ':' )+ )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:76:8: ( 'a' .. 'z' | '0' .. '9' | '_' | '-' | '\\.' | ':' )+
            {
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:76:8: ( 'a' .. 'z' | '0' .. '9' | '_' | '-' | '\\.' | ':' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='-' && LA5_0<='.')||(LA5_0>='0' && LA5_0<=':')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:
            	    {
            	    if ( (input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<=':')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HOST"

    // $ANTLR start "BLOCK"
    public final void mBLOCK() throws RecognitionException {
        try {
            int _type = BLOCK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:79:7: ( OPENBLOCK (~ CLOSEBLOCK )* CLOSEBLOCK )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:79:9: OPENBLOCK (~ CLOSEBLOCK )* CLOSEBLOCK
            {
            mOPENBLOCK(); 
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:79:19: (~ CLOSEBLOCK )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='\u0000' && LA6_0<='\\')||(LA6_0>='^' && LA6_0<='\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:79:20: ~ CLOSEBLOCK
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\u0016')||(input.LA(1)>='\u0018' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            mCLOSEBLOCK(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BLOCK"

    // $ANTLR start "OPENBLOCK"
    public final void mOPENBLOCK() throws RecognitionException {
        try {
            int _type = OPENBLOCK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:84:2: ( '[' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:84:4: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPENBLOCK"

    // $ANTLR start "CLOSEBLOCK"
    public final void mCLOSEBLOCK() throws RecognitionException {
        try {
            int _type = CLOSEBLOCK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:88:2: ( ']' )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:88:4: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSEBLOCK"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:91:4: ( ( ' ' | '\\t' | '\\f' | '\\n' ) )
            // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:91:6: ( ' ' | '\\t' | '\\f' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\f'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:8: ( T__25 | PROXY | ROUTE | BEGIN | END | STUB | PORT | RESPONSE | HEADER | BODY | OUTPROXY | DEBUG | EQ | NUMBER | IDENTIFIER | STRING | URL | HOST | BLOCK | OPENBLOCK | CLOSEBLOCK | WS )
        int alt7=22;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:10: T__25
                {
                mT__25(); 

                }
                break;
            case 2 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:16: PROXY
                {
                mPROXY(); 

                }
                break;
            case 3 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:22: ROUTE
                {
                mROUTE(); 

                }
                break;
            case 4 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:28: BEGIN
                {
                mBEGIN(); 

                }
                break;
            case 5 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:34: END
                {
                mEND(); 

                }
                break;
            case 6 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:38: STUB
                {
                mSTUB(); 

                }
                break;
            case 7 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:43: PORT
                {
                mPORT(); 

                }
                break;
            case 8 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:48: RESPONSE
                {
                mRESPONSE(); 

                }
                break;
            case 9 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:57: HEADER
                {
                mHEADER(); 

                }
                break;
            case 10 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:64: BODY
                {
                mBODY(); 

                }
                break;
            case 11 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:69: OUTPROXY
                {
                mOUTPROXY(); 

                }
                break;
            case 12 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:78: DEBUG
                {
                mDEBUG(); 

                }
                break;
            case 13 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:84: EQ
                {
                mEQ(); 

                }
                break;
            case 14 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:87: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 15 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:94: IDENTIFIER
                {
                mIDENTIFIER(); 

                }
                break;
            case 16 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:105: STRING
                {
                mSTRING(); 

                }
                break;
            case 17 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:112: URL
                {
                mURL(); 

                }
                break;
            case 18 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:116: HOST
                {
                mHOST(); 

                }
                break;
            case 19 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:121: BLOCK
                {
                mBLOCK(); 

                }
                break;
            case 20 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:127: OPENBLOCK
                {
                mOPENBLOCK(); 

                }
                break;
            case 21 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:137: CLOSEBLOCK
                {
                mCLOSEBLOCK(); 

                }
                break;
            case 22 :
                // C:\\development\\intercept\\trunk\\src\\intercept\\configuration\\Configuration.g:1:148: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\1\uffff\1\24\2\16\2\uffff\5\16\1\37\1\16\3\uffff\1\40\4\uffff"+
        "\12\16\3\uffff\13\16\1\67\2\16\1\72\2\16\1\75\2\16\1\100\1\uffff"+
        "\1\101\1\16\1\uffff\1\16\1\17\1\uffff\1\16\1\106\2\uffff\1\16\1"+
        "\110\1\uffff\1\16\1\uffff\1\16\1\uffff\1\16\1\114\1\16\1\uffff\5"+
        "\17\1\123\1\uffff";
    static final String DFA7_eofS =
        "\124\uffff";
    static final String DFA7_minS =
        "\1\11\1\76\2\55\2\uffff\7\55\3\uffff\1\0\4\uffff\12\55\3\uffff"+
        "\25\55\1\uffff\2\55\1\uffff\1\55\1\57\1\uffff\2\55\2\uffff\2\55"+
        "\1\uffff\1\55\1\uffff\1\55\1\uffff\3\55\1\uffff\1\160\1\162\1\157"+
        "\1\170\1\171\1\55\1\uffff";
    static final String DFA7_maxS =
        "\1\175\1\76\2\172\2\uffff\7\172\3\uffff\1\uffff\4\uffff\12\172"+
        "\3\uffff\25\172\1\uffff\2\172\1\uffff\1\172\1\57\1\uffff\2\172\2"+
        "\uffff\2\172\1\uffff\1\172\1\uffff\1\172\1\uffff\3\172\1\uffff\1"+
        "\160\1\162\1\157\1\170\1\171\1\172\1\uffff";
    static final String DFA7_acceptS =
        "\4\uffff\1\4\1\5\7\uffff\1\20\1\17\1\22\1\uffff\1\25\1\26\1\1\1"+
        "\15\12\uffff\1\16\1\24\1\23\25\uffff\1\7\2\uffff\1\6\2\uffff\1\12"+
        "\2\uffff\1\2\1\3\2\uffff\1\21\1\uffff\1\14\1\uffff\1\11\3\uffff"+
        "\1\10\6\uffff\1\13";
    static final String DFA7_specialS =
        "\20\uffff\1\0\103\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\22\1\uffff\1\22\23\uffff\1\22\1\uffff\1\15\12\uffff\2\17"+
            "\1\uffff\12\13\1\17\2\uffff\1\1\3\uffff\32\16\1\20\1\uffff\1"+
            "\21\1\uffff\1\14\1\uffff\1\14\1\10\1\14\1\12\3\14\1\7\6\14\1"+
            "\11\1\2\1\14\1\3\1\6\7\14\1\4\1\uffff\1\5",
            "\1\23",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\16\14\1\26\2\14"+
            "\1\25\10\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\4\14\1\30\11\14"+
            "\1\27\13\14",
            "",
            "",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\23\14\1\31\6\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\4\14\1\32\16\14"+
            "\1\33\6\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\16\14\1\34\13\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\24\14\1\35\5\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\4\14\1\36\25\14",
            "\2\17\1\uffff\12\13\1\17\44\uffff\1\17\1\uffff\32\17",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\32\14",
            "",
            "",
            "",
            "\0\41",
            "",
            "",
            "",
            "",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\16\14\1\42\13\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\21\14\1\43\10\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\24\14\1\44\5\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\22\14\1\45\7\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\24\14\1\46\5\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\1\47\31\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\23\14\1\50\6\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\3\14\1\51\26\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\23\14\1\52\6\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\1\14\1\53\30\14",
            "",
            "",
            "",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\27\14\1\54\2\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\23\14\1\55\6\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\23\14\1\56\6\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\17\14\1\57\12\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\1\14\1\60\30\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\3\14\1\61\26\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\17\14\1\62\12\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\30\14\1\63\1\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\6\14\1\64\23\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\24\14\1\65\5\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\30\14\1\66\1\14",
            "\2\17\1\uffff\13\17\6\uffff\32\16\4\uffff\1\14\1\uffff\32"+
            "\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\4\14\1\70\25\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\16\14\1\71\13\14",
            "\2\17\1\uffff\13\17\6\uffff\32\16\4\uffff\1\14\1\uffff\32"+
            "\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\4\14\1\73\25\14",
            "\2\17\1\uffff\12\17\1\74\44\uffff\1\14\1\uffff\32\14",
            "\2\17\1\uffff\13\17\6\uffff\32\16\4\uffff\1\14\1\uffff\32"+
            "\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\16\14\1\76\13\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\6\14\1\77\23\14",
            "\2\17\1\uffff\13\17\6\uffff\32\16\4\uffff\1\14\1\uffff\32"+
            "\14",
            "",
            "\2\17\1\uffff\13\17\6\uffff\32\16\4\uffff\1\14\1\uffff\32"+
            "\14",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\15\14\1\102\14\14",
            "",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\21\14\1\103\10\14",
            "\1\104",
            "",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\10\14\1\105\21\14",
            "\2\17\1\uffff\13\17\6\uffff\32\16\4\uffff\1\14\1\uffff\32"+
            "\14",
            "",
            "",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\22\14\1\107\7\14",
            "\2\17\1\uffff\13\17\6\uffff\32\16\4\uffff\1\14\1\uffff\32"+
            "\14",
            "",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\15\14\1\111\14\14",
            "",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\4\14\1\112\25\14",
            "",
            "\2\17\1\uffff\13\17\44\uffff\1\14\1\uffff\6\14\1\113\23\14",
            "\2\17\1\uffff\13\17\6\uffff\32\16\4\uffff\1\14\1\uffff\32"+
            "\14",
            "\1\115\1\17\1\uffff\13\17\44\uffff\1\14\1\uffff\32\14",
            "",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\2\17\1\uffff\13\17\44\uffff\1\17\1\uffff\32\17",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__25 | PROXY | ROUTE | BEGIN | END | STUB | PORT | RESPONSE | HEADER | BODY | OUTPROXY | DEBUG | EQ | NUMBER | IDENTIFIER | STRING | URL | HOST | BLOCK | OPENBLOCK | CLOSEBLOCK | WS );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_16 = input.LA(1);

                        s = -1;
                        if ( ((LA7_16>='\u0000' && LA7_16<='\uFFFF')) ) {s = 33;}

                        else s = 32;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 7, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}