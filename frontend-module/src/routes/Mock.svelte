<script>
    import { JSONEditor } from 'svelte-jsoneditor'
    import { createEventDispatcher } from 'svelte';

    export let mock;

    let content = {
        json: mock.response
    }

    const dispatch = createEventDispatcher();

    function propagateUpdate(update) {
        dispatch(
            "mockUpdate", 
            {
                identifier: mock.name, 
                response: update
            }
        )
    }

    function handleChange(updatedContent, previousContent, { contentErrors, patchResult }) {
        content = updatedContent
        propagateUpdate(content.json)
    }
</script>

<div>
    <h3>{mock.name}</h3>
    <p><span>{mock.method}</span> {mock.endpoint}</p>
    <JSONEditor {content} onChange="{handleChange}" mode='tree'/>
</div>


<style>
    span{
        color: red;
    }
</style>