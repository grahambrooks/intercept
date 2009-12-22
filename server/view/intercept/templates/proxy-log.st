<html>
<head>
    <title>Proxy Configuration - $proxy.name$</title>
    <link rel="stylesheet" type="text/css" href="/intercept.css"/>
</head>
<body>
<div class="content">
    <div class="title">Proxy server "$proxy.name$" Logs</div>
    <a class="back" href="/$proxy.name$">Back to $proxy.name$ Configuration</a>
    <form action="/$proxy.name$/log/reset" method="POST" name="resetlogs">
        <button type="submit">Clear Log</button>
    </form>
    <table>
        $logs: {
            <tr>
                $it.elements: {
                <td>$it.message$</td>
                }$
            </tr>
        }$
    </table>
</div>
</body>
</html>