<html>
<head>
    <title>Proxy Configuration - $proxy.name$</title>
    <link rel="stylesheet" type="text/css" href="/intercept.css"/>
</head>
<body>
<div class="content">
    <div class="title">Proxy server "$proxy.name$"</div>
    <a class="back" href="/">Home</a>

    <div class="configuration">
        <table>
            <tr>
                <td>Log level</td>
                <td>$proxy.logLevel$</td>
            </tr>
            <tr>
                <td>Listening on port</td>
                <td>$proxy.port$</td>
            </tr>
            <tr>
                <td>Outgoing proxy host</td>
                <td>$proxy.OutgoingProxyHost$</td>
            </tr>
            <tr>
                <td>Outgoing proxy host</td>
                <td>$proxy.OutgoingProxyPort$</td>
            </tr>
        </table>
    </div>

    <a href="/$proxy.name$/log">Proxy Log</a>

    <div class="subtitle">Stubs</div>

    <form class="stubform" action="/$proxy.name$/stub/new" method="POST" name="new-stub">
        <fieldset id="stub">
            <label for="path">Path to stub</label>
            <input type="text" id="path" name="path"/>
            <label for="response">Enter the text to be returned to the client browser when the path is requested</label>
            <input type="text" id="response" name="response"/>
            <button type="submit">Create Stub</button>
        </fieldset>
    </form>

    <div class="stublist">
        <table>
            <tr>
                <th>Stubbed path</th>
                <th>Response Data</th>
            </tr>
            $stubs: {
            <tr>
                <td>$it.path$</td>
                <td>$it.response$</td>
            </tr>
            }$
        </table>
    </div>

    <div class="subtitle">Routes</div>
    <div class="routelist">
        <table>
            $routes: {
            <tr>
                <td>$it.pattern$</td>
                <td>$it.address$</td>
            </tr>
            }$
        </table>
    </div>
</div>
</body>
</html>