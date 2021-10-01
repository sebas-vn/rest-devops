const url = `http://localhost:5000/api/users/add`;

let btn = document.getElementById('btn');

btn.addEventListener("click", addUser);

function addUser() {
  

  let firstName = document.getElementById('firstName').value;
  let lastName = document.getElementById('lastName').value;
  let username = document.getElementById('username').value;
  let password = document.getElementById('password').value;
  let email = document.getElementById('email').value;

  let user = {
    firstName: firstName,
    lastName: lastName,
    username: username,
    password: password,
    email: email
  }

  const options = {
    method: 'POST',
    body: JSON.stringify(user),
    headers: {
      'Content-Type': 'application/json'
    }
  }

  const options2 = new Request(url, {
    method: 'POST',
    body: JSON.stringify(user),
    headers: new Headers({
      'Content-Type': 'application/json'
    })
  })

  fetch(url, options)
  .then(res => res.json())
  .then(res => console.log(res));
}