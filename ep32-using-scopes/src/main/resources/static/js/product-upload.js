document.addEventListener('DOMContentLoaded', () => {
	
	const uploadBtn = document.querySelector('#uploadBtn')
	const uploadForm = document.querySelector('#uploadForm')
	const fileInput = uploadForm.querySelector('input[type="file"]')
	
	uploadBtn.addEventListener('click', () => fileInput.click())
	fileInput.addEventListener('change', () => uploadForm.submit())
})