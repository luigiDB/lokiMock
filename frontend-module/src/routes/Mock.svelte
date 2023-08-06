<script>
    import { JSONEditor } from 'svelte-jsoneditor'

    export let mock;

    let response = {
        json: mock.response
    }
</script>

<div>
    <h3>{mock.name}</h3>
    <p><span>{mock.method}</span> {mock.endpoint}</p>
    <JSONEditor bind:content={response} mode='tree'/>
</div>


<style>
    span{
        color: red;
    }
</style>