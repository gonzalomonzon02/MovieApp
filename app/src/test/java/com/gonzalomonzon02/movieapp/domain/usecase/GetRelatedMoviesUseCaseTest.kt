package com.gonzalomonzon02.movieapp.domain.usecase

import com.gonzalomonzon02.movieapp.domain.repository.MovieRepository
import com.gonzalomonzon02.movieapp.utils.TestMovieSamples
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GetRelatedMoviesUseCaseTest {
    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var getRelatedMoviesUseCase: GetRelatedMoviesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getRelatedMoviesUseCase = GetRelatedMoviesUseCase(movieRepository)
    }

    @Test
    fun `get related movie from remote`() = runTest {
        val movieIdExpected = 3

        whenever(
            movieRepository.getRelatedMovies(
                movieId = movieIdExpected
            )
        ).thenReturn(
            listOf(TestMovieSamples.movie.copy(id = movieIdExpected))
        )

        val result = getRelatedMoviesUseCase(movieId = movieIdExpected)

        Assert.assertNotNull(result)
        Assert.assertEquals(1, result.size)
        Assert.assertEquals(movieIdExpected, result.first().id)
    }

    @Test
    fun `invoke returns null when repository returns null`() = runTest {
        val movieId = 123

        whenever(
            movieRepository.getRelatedMovies(movieId = movieId)
        ).thenReturn(
            null
        )

        val result = getRelatedMoviesUseCase.invoke(movieId = movieId)

        assertNull(result)
    }
}
