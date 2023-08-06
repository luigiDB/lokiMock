<script>
    import { JSONEditor } from 'svelte-jsoneditor'
    import Mock from './Mock.svelte';

    export let data;

    let requestJson = {
        json: data.request.request
    }

    $: randomResponse = new Promise((yes, no) => {})
    function getRandom() {
        return fetch("http://0.0.0.0:8080/server/random")
            .then(response => response.text())
    }

    $: serviceResponse = null
    function callService() {
        return fetch(data.request.endpoint, {
            method: data.request.method.toUpperCase()
        })
            .then(response => serviceResponse = response.text())
    }

    function handleMockUpdate(event) {
        const match = data.mocks
            .find(mock => mock.name === event.detail.identifier);
        if(match) {
            match.response = event.detail.response
        }
	}
</script>

{#await randomResponse}
    <h1>Waiting for Random number</h1>
{:then value} 
<h1>Random: {value}</h1>
{/await}
<button on:click={() => randomResponse = getRandom()}>generate new random</button>

<h1>{data.title}</h1>

<button on:click={callService}>Execute request</button>

<div class="flex-container">
    <div class="flex-child">
        <p><span>{data.request.method}</span> {data.request.endpoint}</p>
        <JSONEditor bind:content={requestJson} mode='tree'/>
        <div>
            <h2>Response</h2>
            {#if serviceResponse}
                {#await serviceResponse}
                    <h1>Waiting for response</h1>
                {:then value} 
                    <p>{value}</p>
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