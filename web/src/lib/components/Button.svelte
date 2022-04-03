<script lang="ts">
    export let enabled: boolean = true
</script>

<button
    class="p-2 border-2 border-solid rounded-sm border-gray-500 
    text-gray-500 cursor-default opacity-50
    enabled:opacity-100
    enabled:hover:bg-gray-500 enabled:hover:text-white enabled:hover:cursor-pointer"
    class:enabled
    on:click
>
    <slot />
</button>
