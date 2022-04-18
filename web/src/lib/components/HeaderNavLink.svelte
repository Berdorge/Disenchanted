<script context="module" lang="ts">
    export enum FullColorCondition {
        EQUALS,
        BEGINS_WITH
    }
</script>

<script lang="ts">
    import { page } from "$app/stores"
    import { beginsWith } from "$lib/utils/String"

    export let fullColorWhen: FullColorCondition = FullColorCondition.BEGINS_WITH
    export let title: string
    export let href: string

    let isBold: boolean

    $: if (fullColorWhen == FullColorCondition.BEGINS_WITH) {
        isBold = beginsWith($page.routeId || "", href)
    } else {
        isBold = $page.routeId == href
    }
</script>

<a
    class="block p-2 text-center text-lg h-full px-2 hover:text-black font-bold"
    href="/{href}"
    class:text-black={isBold}
    class:text-gray-500={!isBold}
>
    {title}
</a>
