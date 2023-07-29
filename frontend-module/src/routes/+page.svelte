<script>
    import { JSONEditor } from 'svelte-jsoneditor'
    import Mock from './Mock.svelte';

    export let data;

    let original = {
        json: data
    }

    let requestJson = {
        json: data.request
    }

    $: responseFromServer = 'foo'

    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://0.0.0.0:8080//server/random");
    xhr.send();
    xhr.onload = () => {
    if (xhr.readyState == 4 && xhr.status == 200) {
        console.log(xhr.responseText);
        responseFromServer = xhr.responseText

    } else {
        console.log(`Error: ${xhr.status}`);
    }
    };
    
</script>

<h1>{responseFromServer}</h1>

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