<script>
    import { JSONEditor } from 'svelte-jsoneditor'
    import Mock from './Mock.svelte';

    export let data;

    let requestJson = {
        json: data.request.request
    }

    $: serviceResponse = null
    async function callService() {
        fetch("http://0.0.0.0:8080/mockConfiguration", {
            method: 'DELETE'
        })
            .then((_) => {
                data.mocks.map(element => {
                    configureMock(element)
                });
            })
            .then((_) => {
                fetch(data.request.endpoint, {
                    method: data.request.method.toUpperCase()
                })
                .then(async response => serviceResponse = {
                    text: await response.text()
                })
            })
    }

    async function configureMock(element) {
        fetch("http://0.0.0.0:8080/mockConfiguration", {
                method: 'POST', 
                headers: {
                "Content-Type": "application/json",
            },
                body: JSON.stringify(
                    {
                        "method": element.method,
                        "endpoint": element.endpoint,
                        "request": "",
                        "response": JSON.stringify(element.response)
                    }
                )
            })
            .then(response => {
                if(response.ok) {
                    console.log("Configured", element.endpoint);
                } else {
                    console.error("Canot configure", element.endpoint);
                }
            })
        }

    function handleMockUpdate(event) {
        const match = data.mocks
            .find(mock => mock.name === event.detail.identifier);
        if(match) {
            match.response = event.detail.response
        }
	}
</script>

<h1>{data.title}</h1>

<button on:click={callService}>Execute request</button>

<div class="flex-container">
    <div class="flex-child">
        <p><span>{data.request.method}</span> {data.request.endpoint}</p>
        <JSONEditor bind:content={requestJson} mode='tree'/>
        <div>
            {#if serviceResponse}
                <h2>Response</h2>
                {#await serviceResponse}
                    <h1>Waiting for response</h1>
                {:then value} 
                    <JSONEditor bind:content={serviceResponse} mode='tree'/>
                {/await}
            {/if}
        </div>
    </div>
    
    <div class="flex-child">
        <h2>Mocks</h2>

        {#each data.mocks as mock} 
                <Mock bind:mock on:mockUpdate={handleMockUpdate}></Mock>
        {/each}

    </div>
</div>

<style>
    .flex-container {
        display: flex;
    }

    .flex-child {
        flex: 1;
        border: 2px solid yellow;
    }  

    .flex-child:first-child {
        margin-right: 20px;
    } 

    span{
        color: red;
    }
</style>