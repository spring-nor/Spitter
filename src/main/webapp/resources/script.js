var pentaho_iframe = document.querySelector('#pentaho-iframe');

populateIframe(pentaho_iframe, 'http://localhost:8080/pentaho/api/repos/%3Apublic%3ASteel%20Wheels%3AMySalesDashboard.xdash/viewer', [['Authorization', 'Basic QWRtaW46cGFzc3dvcmQ=']]);


function populateIframe(iframe, url, headers) {
    var xhr = new XMLHttpRequest();

    xhr.open('GET', url);
    xhr.onreadystatechange = handler;
    xhr.responseType = 'text';
    headers.forEach(function (header) {
        xhr.setRequestHeader(header[0], header[1]);
    });
    xhr.send();

    function handler() {
        if (this.readyState === this.DONE) {
            if (this.status === 200) {
                // this.response is a Blob, because we set responseType above
                iframe.src = URL.createObjectURL(this.response);
            } else {
                console.error('XHR failed', this);
            }
        }
    }


}

function setPDF(blob) {
    document.querySelector('#blob').src = URL.createObjectURL(blob);
}
