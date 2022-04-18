<script lang="ts">
    import type { DescribedActivity } from "$lib/activity/DescribedActivity"
    import { notEmpty } from "$lib/utils/Array"

    export let activity: DescribedActivity

    let opened = false

    function clicked() {
        opened = !opened
    }
</script>

<div
    class="flex items-center p-2 border-gray-500 border-b-2 border-solid hover:cursor-pointer"
    on:click={clicked}
>
    {#if activity.logoUrl}
        <img class="w-16 h-16 mr-4" src={activity.logoUrl} alt="Логотип" />
    {/if}
    <a
        href="/activities/{activity.id}"
        class="underline text-center text-l p-1"
        on:click|stopPropagation
    >
        {activity.name}
    </a>
    {#if notEmpty(activity.descriptions)}
        <div class="flex-1" />
        <span class="text-l">
            {#if opened}
                ▲
            {:else}
                ▼
            {/if}
        </span>
    {/if}
</div>

{#if notEmpty(activity.descriptions) && opened}
    <div class="px-2 pt-1 descriptions grid gap-2 grid-cols-[minmax(auto,10rem)_1fr]">
        {#each activity.descriptions as description}
            <p class="font-bold">
                {description.name}
            </p>
            <p class="text-center">
                {description.content}
            </p>
        {/each}
    </div>
{/if}
