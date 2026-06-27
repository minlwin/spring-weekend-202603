document.addEventListener('DOMContentLoaded', () => {
	const csrfToken = document.querySelector('meta[name="_csrf"]')?.content || '';
	const csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.content || 'X-CSRF-TOKEN';

	document.addEventListener('click', async (event) => {
		const button = event.target.closest('.js-cart-add, .js-cart-remove');

		if (!button) {
			return;
		}

		const endpoint = button.dataset.endpoint;
		if (!endpoint) {
			return;
		}

		const label = button.innerHTML;
		button.disabled = true;
		button.innerHTML = '<span class="spinner-border spinner-border-sm" aria-hidden="true"></span>';

		try {
			const response = await fetch(endpoint, {
				method: 'POST',
				headers: csrfToken ? { [csrfHeader]: csrfToken } : {}
			});

			if (!response.ok) {
				throw new Error('Request failed');
			}

			window.location.reload();
		} catch (error) {
			button.disabled = false;
			button.innerHTML = label;
		}
	});
});
