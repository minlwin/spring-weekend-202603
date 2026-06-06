document.addEventListener('DOMContentLoaded', () => {
	const ids = [
		"studentNae", 
		"studentPhone",
		"studentEmail",
		"fatherName",
		"fatherPhone",
		"fatherOcupation",
		"motherName",
		"motherPhone",
		"motherOcupation"
	]
	
	const elements = {}
	ids.forEach(id => {
		elements[id] = document.querySelector(`#${id}`)
	})
	
	const loadStudent = async () => {
		if(elements['studentName'].value 
			&& elements['studentPhone'].value 
			&& elements['studentEmail'].value) {
			
			const path = /*[[@{/students/registrations}]]*/ '/students/registration'
			const params = new URLSearchParams({
				name: elements['studentName'].value,
				phone: elements['studentPhone'].value,
				email: elements['studentEmail'].value
			})

			const response = await fetch(`${path}?${params.toString()}`, {
				method: 'GET',
				headers: {
					'Accept' : 'application/json'
				}
			})

			if(response.ok) {
				const data = await response.json()
				
				Object.entries(elements)
					.filter(entry => !entry[0].startsWith("student"))
					.filter(entry => entry[1])
					.forEach(entry => entry[1].value = data[entry[0]])
			}
		}
	}
	
	Object.entries(elements)
		.filter(entry => entry[0].startsWith("student"))
		.map(entry => entry[1])
		.forEach(input => input.addEventListener('change', loadStudent))
})