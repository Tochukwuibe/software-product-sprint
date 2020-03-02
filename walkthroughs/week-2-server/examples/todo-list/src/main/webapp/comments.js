const api = "https://8080-dot-10864018-dot-devshell.appspot.com"

window.addEventListener("DOMContentLoaded", () => {

    const send_button = document.getElementById('send-button');
    const text_input = document.getElementById('send-input');



    send_button.addEventListener('click', async () => {
        console.log("the target ", text_input.value);
        try {
            postMessage(text_input.value);
            text_input.value = "";
        } catch (e) {

        }

    });


});


async function postMessage(text) {

    const params = new URLSearchParams();
    params.append('text', text);

    const res = await fetch(`${api}/comments`, {
        method: 'POST',
        body: params
    });

    console.log('the response ', res);

}


