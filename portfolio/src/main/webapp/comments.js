const api = "https://8080-dot-10864018-dot-devshell.appspot.com"

window.addEventListener("DOMContentLoaded", () => {

    setMessages();

    const send_button = document.getElementById('send-button');
    const text_input = document.getElementById('send-input');



    send_button.addEventListener('click', () => {
        postMessage(text_input.value);
        text_input.value = "";
    });
    text_input.addEventListener('keyup', (event) => {
        if (event.keyCode === 13) {
            postMessage(text_input.value);
            text_input.value = "";
        }
    })



});






function appendMessage({ text }) {
    const container = document.getElementById('messages');
    const message_div = document.createElement('div')
    message_div.classList.add('card');
    message_div.classList.add('msg-bubble');
    message_div.innerText = text;
    container.appendChild(message_div);
}


async function setMessages() {
    const messages = await getMessages();
    console.log('the messages ', messages);
    messages.forEach(appendMessage);
}

async function getMessages() {
    const res = await fetch(`${api}/comments`, {
        method: 'GET'
    });

    return await res.json();
}


async function postMessage(text) {

    try {

        const params = new URLSearchParams();
        params.append('text', text);
        appendMessage({ text });
        const res = await fetch(`${api}/comments`, { method: 'POST', body: params });
        console.log('the response ', res);

    } catch (e) {

    }

}


