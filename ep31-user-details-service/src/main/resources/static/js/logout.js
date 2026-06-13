document.addEventListener('DOMContentLoaded', () => {
	const logoutMenu = document.getElementById('logoutMenu')
	const logoutForm = document.getElementById('logoutForm')
	
	logoutMenu.addEventListener('click', event => {
		event.preventDefault()
		logoutForm.submit()
	})
})