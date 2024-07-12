// 페이지 전환 없이 응답데이터만 받아보려고 하는중...
// form 객체를 일반 객체로 전환하는거래

function handleSubmitAllClick() {
	const forms = document.querySelectorAll("form");
	const formData1 = new FormData(forms[0]);
	const formData2 = new FormData(forms[1]);

	let reqData = {};
	
	/*
		formData1 = {
			username: "admin",
			password: "1234"
		} 
		이거를 
		entries = [
			["username", "admin"], 
			["password", "1234"]
		]
		얘로 바꿔서 entry에 넣고 -> reqData에 넣기..
	*/
	
	// 첫번째 form (form.jsp의 form) 을 일반 객체화 시키는 용도
	for(let entry of formData1.entries()) {
		const [ key, value ] = entry;
		reqData = {
			...reqData,
			[key]: value // 추가 > username:admin, password:1234
		}
	} 

	/*
		formData2 = {
			chk: "chk1",
			chk: "chk2",
			rdo: "rdo1"
		} 
		이게 
		entries = [
			["chk", "chk1"], 
			["chk", "chk2"],
			["rdo", "rdo1"]
		]
		얘로 바뀜
	*/
	
	
	// key 값들 중복체크용 ( chk는 하나의 키에 두개의 값이 존재할 수 있기 때문에 chk 는 배열로 하나의 키에 두개의 값을 받아줘야함 )
	let duplicatedKeys = [];
	let keyCount = {}
	
	// 두번째 form
	for(let key of formData2.keys()) { // 두번째 form에서 key들을 뽑음
		if(Object.keys(keyCount).includes(key)) { // 키 값이 있으면
			keyCount = {
				...keyCount,
				[key]: keyCount[key] + 1
				}
				continue;
			}
			keyCount = { // 키 값이 없으면 [chk]: 1
				...keyCount,
				[key]: 1
			}
		}

	// keycount 가 key가 2개 이상인 애들 찾기 ( key 가 중복인 애들만 찾기 )
	for(let key of Object.keys(keyCount)) {
		if(keyCount[key] > 1) {
			duplicatedKeys = [ ...duplicatedKeys, key ];
		}
	}
	
	console.log(keyCount);
	console.log(duplicatedKeys);


	for(let entry of formData2.entries()) {
		const [ key, value ] = entry;
		if(duplicatedKeys.includes(key)) { // 키가 중복인 애라면
			reqData = { // 하나의 키에 여러개의 값을 넣겠따 (배열)
				...reqData,
				[key]: [ ...(!reqData[key] ? [] : reqData[key]), value ] // 빈값이라면 빈 배열을, 값이 있다면 reqData로 가라!! 그리고 새로운 값을 추가
				}
				continue;
			}
			reqData = { // 하나의 키에 하나의 값만 있다면 그냥 추가해라
				...reqData,
				[key]: value
			}
		}	
		
	console.log(reqData);
	
	const queryString = new URLSearchParams(reqData).toString();
	
	fetch(`http://localhost:8080/dvd/form?${queryString}`)
	.then(response => {
		response.text()
		.then(data => {
			const body = document.querySelector("body");
			body.innerHTML += `<h1>${data}</h1>`;
			console.log(data);
		}) 
	})
}
	