document.addEventListener('DOMContentLoaded', () => {
	const uploadBtn = document.querySelector('#uploadBtn');
	const fileInput = document.querySelector('#fileInput');
	const uploadForm = document.querySelector('#uploadForm');
	
	uploadBtn.addEventListener('click', () => fileInput.click());
	fileInput.addEventListener('change', () => uploadForm.submit());
});