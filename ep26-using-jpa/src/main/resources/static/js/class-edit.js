document.addEventListener('DOMContentLoaded', () => {

	const form = document.querySelector("form")
	const actionInput = form.querySelector("#actionInput")
	const deleteInput = form.querySelector("#deleteInput")
	
	const addBtn = document.querySelector("#addBtn")
	const deleteBtns = document.querySelectorAll(".deleteBtn")
	
	addBtn.addEventListener('click', () => {
		actionInput.value = 'addSchedule'
		form.submit()
	})
	
	deleteBtns.forEach((btn, index) => btn.addEventListener('click', () => {
		deleteInput.value = index
		form.submit()
	}))
})