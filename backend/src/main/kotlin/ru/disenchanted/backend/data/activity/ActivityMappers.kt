package ru.disenchanted.backend.data.activity

import ru.disenchanted.backend.domain.activity.Activity
import ru.disenchanted.backend.domain.activity.ChildActivity
import ru.disenchanted.backend.domain.activity.DescribedActivity
import ru.disenchanted.backend.domain.description.Description

fun Activity.withParent(parent: Activity?) = ChildActivity(
    id = id,
    name = name,
    logoUrl = logoUrl,
    parent = parent
)

fun Activity.describedWith(descriptions: List<Description>) = DescribedActivity(
    id = id,
    name = name,
    logoUrl = logoUrl,
    descriptions = descriptions
)
