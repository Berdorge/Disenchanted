<script lang="ts">
    import { Activity } from "$lib/activity/Activity"
    import { UniversityProgramActivityApi } from "$lib/activity/UniversityProgramActivityApi"
    import { empty } from "$lib/utils/Array"
    import { onMount } from "svelte/internal"
    import { container } from "tsyringe"

    export let universityId: string
    export let programId: string | null = null

    let loading = true
    let activities: Array<Activity>

    onMount(() => {
        const api = container.resolve(UniversityProgramActivityApi)
        api.getUniversityActivities(universityId)
            .then((apiActivities) => (activities = apiActivities))
            .then(() => (loading = false))
    })
</script>

{#if loading}
    <p class="text-gray-800 text-sm text-center">Загружаем...</p>
{:else if empty(activities)}
    <h2 class="text-xl text-gray-800 text-center p-2">Активностей нет :(</h2>
{:else}
    <h2 class="text-xl text-gray-800 text-center p-2">Активности</h2>
    {#each activities as activity}
        <a href="/activities/${activity.id}" class="text-center underline p-1">
            {activity.name}
        </a>
    {/each}
{/if}
