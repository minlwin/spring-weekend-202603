document.addEventListener('DOMContentLoaded', () => {
	const csrfToken = document.querySelector('meta[name="_csrf"]')?.content || '';
	const csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.content || 'X-CSRF-TOKEN';
	const cartCountDisplay = document.querySelector('.js-cart-count');
	const cartCountItem = cartCountDisplay?.closest('.nav-item');

	document.querySelectorAll('.js-add-to-cart').forEach((button) => {
		button.addEventListener('click', async () => {
			const endpoint = button.dataset.endpoint;
			const label = button.textContent;
			button.disabled = true;
			button.textContent = 'Adding...';

			try {
				const response = await fetch(endpoint, {
					method: 'POST',
					headers: csrfToken ? { [csrfHeader]: csrfToken } : {}
				});

				if (!response.ok) {
					throw new Error('Request failed');
				}

				button.textContent = 'Added';

				const cart = await response.json();
				if (cartCountDisplay) {
					cartCountDisplay.textContent = cart.count;
				}

				if (cartCountItem) {
					cartCountItem.classList.toggle('d-none', cart.count === 0);
				}
			} catch (error) {
				button.textContent = label;
			} finally {
				window.setTimeout(() => {
					button.disabled = false;
					button.textContent = label;
				}, 900);
			}
		});
	});
});
