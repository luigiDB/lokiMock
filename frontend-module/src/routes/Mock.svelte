<script>
    import { JSONEditor } from 'svelte-jsoneditor'

    export let mock;

    let request = {
        json: mock.request
    }

    let response = {
        json: mock.response
    }
</script>

<div>
    <h3>{mock.name}</h3>
    <p><span>{mock.method}</span> {mock.endpoint}</p>
    <div class="flex-container">
        <div class="flex-child">
            <JSONEditor bind:content={request} mode='tree'/>
        </div>
        <div class="flex-child">
            <JSONEditor bind:content={response} mode='tree'/>
        </div>
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