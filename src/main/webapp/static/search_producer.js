function handleProducerClick() {
	const searchInput = document.querySelector(".producer-input");
	location.href = `http://localhost:8080/dvd/producer/search?searchText=${searchInput.value}`;
}