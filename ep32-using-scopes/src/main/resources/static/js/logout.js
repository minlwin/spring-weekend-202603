document.addEventListener('DOMContentLoaded', () => {
	const logoutMenu = document.querySelector('#logoutMenu');
	const logoutForm = document.querySelector('#logoutForm');
	
	logoutMenu.addEventListener('click', event => {
		event.preventDefault();
		logoutForm.submit();
	})
})