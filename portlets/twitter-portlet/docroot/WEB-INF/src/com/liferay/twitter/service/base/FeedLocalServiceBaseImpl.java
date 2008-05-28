/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.twitter.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;

import com.liferay.twitter.model.Feed;
import com.liferay.twitter.service.FeedLocalService;
import com.liferay.twitter.service.persistence.FeedPersistence;
import com.liferay.twitter.service.persistence.FeedUtil;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * <a href="FeedLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class FeedLocalServiceBaseImpl implements FeedLocalService,
	InitializingBean {
	public Feed addFeed(Feed feed) throws SystemException {
		feed.setNew(true);

		return feedPersistence.update(feed, false);
	}

	public void deleteFeed(long feedId) throws PortalException, SystemException {
		feedPersistence.remove(feedId);
	}

	public void deleteFeed(Feed feed) throws PortalException, SystemException {
		feedPersistence.remove(feed);
	}

	public List<Feed> dynamicQuery(DynamicQueryInitializer queryInitializer)
		throws SystemException {
		return feedPersistence.findWithDynamicQuery(queryInitializer);
	}

	public List<Feed> dynamicQuery(DynamicQueryInitializer queryInitializer,
		int start, int end) throws SystemException {
		return feedPersistence.findWithDynamicQuery(queryInitializer, start, end);
	}

	public Feed updateFeed(Feed feed) throws SystemException {
		feed.setNew(false);

		return feedPersistence.update(feed, true);
	}

	public FeedPersistence getFeedPersistence() {
		return feedPersistence;
	}

	public void setFeedPersistence(FeedPersistence feedPersistence) {
		this.feedPersistence = feedPersistence;
	}

	public void afterPropertiesSet() {
		if (feedPersistence == null) {
			feedPersistence = FeedUtil.getPersistence();
		}
	}

	protected FeedPersistence feedPersistence;
}