package com.android.aop.part2.toycoininformation.domain.model

import com.android.aop.part2.toycoininformation.data.remote.dto.Tag
import com.android.aop.part2.toycoininformation.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<Tag>,
    val team: List<TeamMember>
)
