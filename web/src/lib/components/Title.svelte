<script lang="ts">
    export let caption: string | undefined = undefined
    const title = caption ? `${caption} • Disenchanted` : "Disenchanted"
</script>

<svelte:head>
    <title>
        {title}
    </title>
</svelte:head>
