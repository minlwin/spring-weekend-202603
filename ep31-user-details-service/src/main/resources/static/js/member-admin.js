document.addEventListener('DOMContentLoaded', () => {
	
	const form = document.querySelector('#switchStatusForm')
	const targetId = form.querySelector('#targetId')
	
	document.querySelectorAll('.actionLink')
		.forEach(link => link.addEventListener('click', event => {
			event.preventDefault()
			targetId.value = link.dataset.id
			form.submit()
		}))
})