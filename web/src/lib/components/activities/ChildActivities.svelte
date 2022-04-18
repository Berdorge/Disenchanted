<script lang="ts">
    import type { Activity } from "$lib/activity/Activity"
    import { ActivityApi } from "$lib/activity/ActivityApi"
    import { notEmpty } from "$lib/utils/Array"
    import { container } from "tsyringe"

    export let activityId: string

    let loading = true
    let activities: Array<Activity>

    $: {
        const api = container.resolve(ActivityApi)
        api.getChildActivities(activityId)
            .then((apiActivities) => (activities = apiActivities))
            .then(() => (loading = false))
    }
</script>

{#if loading}
    <p class="text-gray-800 text-sm text-center">Загружаем...</p>
{:else if notEmpty(activities)}
    <h2 class="text-xl text-gray-800 text-center p-2">Дочерние активности</h2>
    {#each activities as activity}
        <a href="/activities/{activity.id}" class="flex justify-between items-center p-2">
            {#if activity.logoUrl}
                <img class="w-16 h-16 mr-4" src={activity.logoUrl} alt="Логотип" />
            {/if}
            <p class="flex-1 underline text-center text-l">{activity.name}</p>
        </a>
    {/each}
{/if}
