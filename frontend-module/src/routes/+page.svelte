<script>
    import { JSONEditor } from 'svelte-jsoneditor'
    import Mock from './Mock.svelte';

    export let data;

    let requestJson = {
        json: data.request
    }

    $: randomResponse = new Promise((yes, no) => {})
    function getRandom() {
        return fetch("http://0.0.0.0:8080/server/random")
            .then(response => response.text())
    }
</script>

{#await randomResponse}
    <h1>Waiting for Random number</h1>
{:then value} 
<h1>Random: {value}</h1>
{/await}
<button on:click={() => randomResponse = getRandom()}>generate new random</button>

<h1>{data.title}</h1>

<div class="flex-container">

    <div class="flex-child">
        <h2>request</h2>
        <JSONEditor bind:content={requestJson} mode='tree'/>
        <br>
    </div>
    
    <div class="flex-child">
        <h2>Mocks</h2>

        {#each data.mocks as mock} 
                <Mock bind:mock></Mock>
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
</style>