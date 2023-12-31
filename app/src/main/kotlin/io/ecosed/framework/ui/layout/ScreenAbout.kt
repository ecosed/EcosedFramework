package io.ecosed.framework.ui.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardCommandKey
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Source
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blankj.utilcode.util.AppUtils
import io.ecosed.framework.BuildConfig
import io.ecosed.framework.R
import io.ecosed.framework.ui.preview.ScreenPreviews
import io.ecosed.framework.ui.theme.EcosedFrameworkTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScreenAbout(
    scope: CoroutineScope,
    snackBarHostState: SnackbarHostState,
    onEasterEgg: () -> Unit,
    onNotice: () -> Unit,
    onSource: () -> Unit,
    onDevGitHub: () -> Unit,
    onDevTwitter: () -> Unit,
    onTeamGitHub: () -> Unit
) {
    val scrollState = rememberScrollState()
    val position: MutableState<Int> = remember {
        mutableStateOf(value = 0)
    }
    /**
     * 通过自定义BuildConfig获取本项目是否参与比赛
     * 如果本项目参与比赛则屏蔽可能影响成绩的敏感因素
     * 对于当下社会因素和价值观问题这是必不可少的做法
     */
    val contest: Boolean = BuildConfig.CONTEST
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = scrollState
                )
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 12.dp,
                        top = 12.dp,
                        end = 12.dp,
                        bottom = 6.dp
                    )
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                all = 24.dp
                            )
                            .height(
                                height = 48.dp
                            )
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.custom_ecosed_24
                            ),
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    start = 16.dp
                                ),
                            contentAlignment = Alignment.CenterStart,
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.app_description
                                ),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 50.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    AppUtils.launchAppDetailsSettings()
                                }
                                .padding(
                                    horizontal = 24.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Column(
                                modifier = Modifier
                                    .weight(
                                        weight = 1f
                                    )
                                    .padding(
                                        start = 16.dp,
                                        end = 5.dp
                                    )
                            ) {
                                Text(
                                    text = stringResource(
                                        id = R.string.version
                                    ),
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = BuildConfig.VERSION_NAME,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(
                                min = 50.dp
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    if (!contest) when (position.value) {
                                        0 -> {
                                            scope.launch {
                                                snackBarHostState.showSnackbar(
                                                    "喵~"
                                                )
                                            }
                                            position.value++
                                        }

                                        1 -> {
                                            scope.launch {
                                                snackBarHostState.showSnackbar(
                                                    "喵喵喵?"
                                                )
                                            }
                                            position.value++
                                        }

                                        2 -> {
                                            scope.launch {
                                                snackBarHostState.showSnackbar(
                                                    "🍥🍥🍥"
                                                )
                                            }
                                            position.value++
                                        }

                                        else -> {
                                            onEasterEgg()
                                            position.value = 0
                                        }
                                    } else AppUtils.launchAppDetailsSettings()
                                }
                                .padding(
                                    horizontal = 24.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Numbers,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Column(
                                modifier = Modifier
                                    .weight(
                                        weight = 1f
                                    )
                                    .padding(
                                        start = 16.dp,
                                        end = 5.dp
                                    )
                            ) {
                                Text(
                                    text = "版本代码",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = BuildConfig.VERSION_CODE.toString(),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(
                                min = 50.dp
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onNotice()
                                }
                                .padding(
                                    horizontal = 24.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.LibraryBooks,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Column(
                                modifier = Modifier
                                    .weight(
                                        weight = 1f
                                    )
                                    .padding(
                                        start = 16.dp,
                                        end = 5.dp
                                    )
                            ) {
                                Text(
                                    text = "开放源代码许可",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(
                                min = 50.dp
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    scope.launch {
                                        scrollState.animateScrollBy(10000f)
                                    }
                                }
                                .padding(
                                    horizontal = 24.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PeopleAlt,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Column(
                                modifier = Modifier
                                    .weight(
                                        weight = 1f
                                    )
                                    .padding(
                                        start = 16.dp,
                                        end = 5.dp
                                    )
                            ) {
                                Text(
                                    text = "开发者",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = "wyq0918dev",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(
                                min = 50.dp
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onSource()
                                }
                                .padding(
                                    horizontal = 24.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Source,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Column(
                                modifier = Modifier
                                    .weight(
                                        weight = 1f
                                    )
                                    .padding(
                                        start = 16.dp,
                                        end = 5.dp
                                    )
                            ) {
                                Text(
                                    text = "源代码",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = contest
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 12.dp,
                            top = 6.dp,
                            end = 12.dp,
                            bottom = 12.dp
                        )
                ) {
                    Column {
                        Text(
                            text = "参赛信息",
                            modifier = Modifier.padding(
                                all = 16.dp
                            ),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(
                                    height = 75.dp
                                )
                        ) {
                            Image(
                                painter = painterResource(
                                    id = R.drawable.custom_nvvision_24
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 24.dp
                                    ),
                                contentScale = ContentScale.Fit
                            )
                        }
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 50.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(
                                    horizontal = 24.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.LocalFlorist,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Column(
                                    modifier = Modifier
                                        .weight(
                                            weight = 1f
                                        )
                                        .padding(
                                            start = 16.dp,
                                            end = 5.dp
                                        )
                                ) {
                                    Text(
                                        text = "活动名称",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = BuildConfig.ACTION_NAME,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 50.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(
                                    horizontal = 24.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.People,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Column(
                                    modifier = Modifier
                                        .weight(
                                            weight = 1f
                                        )
                                        .padding(
                                            start = 16.dp,
                                            end = 5.dp
                                        )
                                ) {
                                    Text(
                                        text = "指导教师",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = BuildConfig.TEACHER_NAME,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 50.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(
                                    horizontal = 24.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardCommandKey,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Column(
                                    modifier = Modifier
                                        .weight(
                                            weight = 1f
                                        )
                                        .padding(
                                            start = 16.dp,
                                            end = 5.dp
                                        )
                                ) {
                                    Text(
                                        text = "项目名称",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = BuildConfig.PROJECT_NAME,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 50.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(
                                    horizontal = 24.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Column(
                                    modifier = Modifier
                                        .weight(
                                            weight = 1f
                                        )
                                        .padding(
                                            start = 16.dp,
                                            end = 5.dp
                                        )
                                ) {
                                    Text(
                                        text = "学生姓名",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = "unknown",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = !contest
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 12.dp,
                            top = 6.dp,
                            end = 12.dp,
                            bottom = 6.dp
                        )
                ) {
                    Column {
                        Text(
                            text = "开发者",
                            modifier = Modifier.padding(
                                all = 16.dp
                            ),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(
                                    min = 80.dp
                                )
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 24.dp
                                        )
                                        .padding(
                                            top = 5.dp
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = R.drawable.custom_developer_24
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(
                                                width = 48.dp,
                                                height = 48.dp
                                            )
                                            .clip(
                                                CircleShape
                                            )
                                    )
                                    Column(
                                        modifier = Modifier
                                            .weight(
                                                weight = 1f
                                            )
                                            .padding(
                                                start = 16.dp,
                                                end = 5.dp
                                            )
                                    ) {
                                        Text(
                                            text = "wyq0918dev",
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Text(
                                            text = "设计/开发/维护/测试/宣传",
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier.padding(
                                        horizontal = 24.dp
                                    )
                                ) {
                                    TextButton(
                                        onClick = {
                                            onDevGitHub()
                                        }
                                    ) {
                                        Text(
                                            text = "GitHub"
                                        )
                                    }
                                    TextButton(
                                        onClick = {
                                            onDevTwitter()
                                        }
                                    ) {
                                        Text(
                                            text = "Twitter"
                                        )
                                    }
                                }
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(
                                    min = 80.dp
                                )
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 24.dp
                                        )
                                        .padding(
                                            top = 5.dp
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = R.drawable.custom_ecosed_24
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier.size(
                                            width = 48.dp,
                                            height = 48.dp
                                        )
                                    )
                                    Column(
                                        modifier = Modifier
                                            .weight(
                                                weight = 1f
                                            )
                                            .padding(
                                                start = 16.dp,
                                                end = 5.dp
                                            )
                                    ) {
                                        Text(
                                            text = "Ecosed Project",
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Text(
                                            text = "项目组织",
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier.padding(
                                        horizontal = 24.dp
                                    )
                                ) {
                                    TextButton(
                                        onClick = {
                                            onTeamGitHub()
                                        }
                                    ) {
                                        Text(
                                            text = "GitHub"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = !contest
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 12.dp,
                            top = 6.dp,
                            end = 12.dp,
                            bottom = 12.dp
                        )
                ) {
                    Column {
                        Text(
                            text = "部分设计源自彩虹的缤纷色彩，旨在支持LGBTQ+群体。",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "不向焦虑与抑郁投降，这个世界终会有我们存在的地方。",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "愿人间少一份伤害，多一份理解与尊重。",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Image(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Fit,
                            colorFilter = ColorFilter.tint(color = Color.Red)
                        )
                    }
                }
            }
        }
    }
}

@ScreenPreviews
@Composable
fun ScreenAboutPreview() {
    EcosedFrameworkTheme {
        ScreenAbout(
            scope = rememberCoroutineScope(),
            snackBarHostState = remember {
                SnackbarHostState()
            },
            onEasterEgg = {},
            onNotice = {},
            onSource = {},
            onDevGitHub = {},
            onDevTwitter = {},
            onTeamGitHub = {}
        )
    }
}